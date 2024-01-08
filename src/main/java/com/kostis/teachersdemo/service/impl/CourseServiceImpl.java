package com.kostis.teachersdemo.service.impl;

import com.kostis.teachersdemo.entities.Course;
import com.kostis.teachersdemo.entities.StudentCourseAssociation;
import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.models.CourseModel;
import com.kostis.teachersdemo.repo.CourseRepository;
import com.kostis.teachersdemo.repo.StudentCourseAssociationRepository;
import com.kostis.teachersdemo.repo.UserRepository;
import com.kostis.teachersdemo.service.ICourseService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements ICourseService {

    private final CourseRepository courseRepository;
    private final StudentCourseAssociationRepository associationRepository;
    private final UserRepository userRepository;
    private final DTOServiceImpl dtoService;

    private final StudentCourseAssociationRepository studentCourseAssociationRepository;

    public CourseServiceImpl(CourseRepository courseRepository, StudentCourseAssociationRepository associationRepository, UserRepository userRepository, DTOServiceImpl dtoService, StudentCourseAssociationRepository studentCourseAssociationRepository) {
        this.courseRepository = courseRepository;
        this.associationRepository = associationRepository;
        this.userRepository = userRepository;
        this.dtoService = dtoService;
        this.studentCourseAssociationRepository = studentCourseAssociationRepository;
    }

    @Override
    public List<CourseModel> getAllCourseModels() {
        return dtoService.convertCourseListToModelList(courseRepository.findAll());
    }

    @Override
    public Course getCourseById(Integer id) {
        return courseRepository.findById(id).orElse(null);
    }

    @Override
    public void saveCourse(CourseModel course) throws Exception {
        Course courseToUpdate = courseRepository.findById(course.getId()).orElse(null);

        if (courseToUpdate!=null){
            courseToUpdate.setName(course.getName());
            courseToUpdate.setDescription(course.getDescription());
            courseToUpdate.setSemester(course.getSemester());

            courseRepository.save(courseToUpdate);
        } else {
            throw new Exception("Null incoming course to update.");
        }
    }

    @Override
    public void deleteCourse(CourseModel course) throws Exception {
        Course courseToDelete = getCourseById(course.getId());

        if (courseToDelete !=null){
            if (courseToDelete.getStudentAssociations()!=null){
                for (StudentCourseAssociation association: courseToDelete.getStudentAssociations()){
                    associationRepository.deleteById(association.getId());
                }
            }

            courseRepository.delete(courseToDelete);
        } else {
            throw new Exception("Null incoming course to delete.");
        }
    }

    @Override
    public void createNewCourse(CourseModel course){
        Course courseToInsert = dtoService.convertModelToCourse(course);
        courseToInsert.setId(null);

        courseRepository.save(courseToInsert);
    }

    @Override
    public void removeTeacherFromCourse(Integer selectedTeacherId, Integer selectedCourseId) throws Exception {
        Course course = getCourseById(selectedCourseId);
        User teacher = userRepository.findById(selectedTeacherId).orElse(null);

        if (teacher.getRole().getId().equals(1) && course.getTeacher().getId().equals(teacher.getId())){
            course.setTeacher(null);
            teacher.getTaughtCourses().remove(course);

            courseRepository.save(course);
        } else {
            throw new Exception("Error at removeTeacherFromCourse(). Mismatch teacher & Course");
        }
    }

    @Override
    public void addTeacherToCourse(Integer selectedTeacherId, Integer selectedCourseId) throws Exception {
        Course course = getCourseById(selectedCourseId);
        User teacher = userRepository.findById(selectedTeacherId).orElse(null);

        if (teacher.getRole().getId().equals(1) && course.getTeacher() == null){
            course.setTeacher(teacher);
            teacher.getTaughtCourses().add(course);

            courseRepository.save(course);
        } else {
            throw new Exception("Error at addTeacherToCourse(). User is not a teacher or course has already a teacher");
        }
    }

    @Override
    public void removeCourseFromStudent(Integer selectedStudentId, Integer selectedCourseId) throws Exception {
        Course course = getCourseById(selectedCourseId);
        User student = userRepository.findById(selectedStudentId).orElse(null);

        if (course!=null && student!=null){
            StudentCourseAssociation association = studentCourseAssociationRepository.findByStudent_IdAndCourse_Id(student.getId(), course.getId());
            if (association!=null && student.getRole().getId().equals(2)){
                studentCourseAssociationRepository.delete(association);
            } else {
                throw new Exception("Error at removeCourseFromStudent(). Cannot find student-course association or user is not a student.");
            }
        } else {
            throw new Exception("Error at removeCourseFromStudent(). Cannot find course or student record at db.");
        }
    }

    @Override
    public void addCourseToStudent(Integer selectedStudentId, Integer selectedCourseId) throws Exception {
        Course course = getCourseById(selectedCourseId);
        User student = userRepository.findById(selectedStudentId).orElse(null);

        if (course!=null && student!=null){
            StudentCourseAssociation association = new StudentCourseAssociation();
            association.setCourse(course);
            association.setStudent(student);

            studentCourseAssociationRepository.saveAndFlush(association);
        } else {
            throw new Exception("Error at addCourseToStudent(). Cannot find course or student record at db.");
        }
    }

    @Override
    public List<CourseModel> customSearchCourses(String searchValue) {
        searchValue = "%" + searchValue + "%";
        List<Course> courseList = courseRepository.findCoursesWithCustomSearch(searchValue);

        return dtoService.convertCourseListToModelList(courseList);
    }

    @Override
    public List<CourseModel> getAllCoursesWithoutTeacher() {
        List<Course> courseList = new ArrayList<>();
        for (Course course: courseRepository.findAll()){
            if (course.getTeacher() == null){
                courseList.add(course);
            }
        }

        return dtoService.convertCourseListToModelList(courseList);
    }

}
