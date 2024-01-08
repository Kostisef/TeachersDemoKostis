package com.kostis.teachersdemo.service.impl;

import com.kostis.teachersdemo.entities.Course;
import com.kostis.teachersdemo.entities.User;
import com.kostis.teachersdemo.models.StudentModel;
import com.kostis.teachersdemo.models.TeacherModel;
import com.kostis.teachersdemo.repo.CourseRepository;
import com.kostis.teachersdemo.repo.StudentCourseAssociationRepository;
import com.kostis.teachersdemo.repo.UserRepository;
import com.kostis.teachersdemo.service.IUserService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserServiceImpl implements IUserService {

    private final UserRepository userRepository;
    private final CourseRepository courseRepository;
    private final StudentCourseAssociationRepository studentCourseAssociationRepository;

    private final DTOServiceImpl dtoService;

    public UserServiceImpl(UserRepository userRepository, CourseRepository courseRepository, StudentCourseAssociationRepository studentCourseAssociationRepository, DTOServiceImpl dtoService) {
        this.userRepository = userRepository;
        this.courseRepository = courseRepository;
        this.studentCourseAssociationRepository = studentCourseAssociationRepository;
        this.dtoService = dtoService;
    }


    /**
     * Find All Teachers
     */
    @Override
    public List<TeacherModel> getAllTeacherModels() {
        List<User> userList = userRepository.findByRole_Name("TEACHER");

        List<TeacherModel> teacherModelList = new ArrayList<>();
        for (User user: userList){
            teacherModelList.add(dtoService.convertUserToTeacherModel(user));
        }
        return teacherModelList;
    }

    /**
     * Find All Students
     */
    @Override
    public List<StudentModel> getAllStudentModels() {
        List<User> userList = userRepository.findByRole_Name("STUDENT");

        List<StudentModel> studentModelList = new ArrayList<>();
        for (User user: userList){
            studentModelList.add(dtoService.convertUserToStudentModel(user));
        }
        return studentModelList;
    }


    @Override
    public User getUserById(Integer id) {
        return userRepository.findById(id).orElse(null);
    }


    /**
     * Save User
     *
     * @param user
     */
    @Override
    public void saveTeacher(TeacherModel teacherModel) throws Exception {
        User userFound = userRepository.findById(teacherModel.getId()).orElse(null);

        if (userFound != null){
            User userToUpdate = dtoService.convertTeacherModelToUser(teacherModel);
            userToUpdate.setId(userFound.getId());
            userToUpdate.setPassword(userFound.getPassword());
            userRepository.save(userToUpdate);
        } else {
            throw new Exception("Null incoming user to update.");
        }
    }

    @Override
    public void saveStudent(StudentModel studentModel) throws Exception {
        User userFound = userRepository.findById(studentModel.getId()).orElse(null);

        if (userFound != null){
            User userToUpdate = dtoService.convertStudentModelToUser(studentModel);
            userToUpdate.setId(userFound.getId());
            userToUpdate.setPassword(userFound.getPassword());
            userRepository.save(userToUpdate);
        } else {
            throw new Exception("Null incoming user to update.");
        }
    }

    @Override
    public void deleteTeacher(TeacherModel teacher){
        User teacherToDelete = getUserById(teacher.getId());
        for (Course course : teacherToDelete.getTaughtCourses()){
            course.setTeacher(null);
        }
        courseRepository.saveAllAndFlush(teacherToDelete.getTaughtCourses());
        userRepository.delete(teacherToDelete);
    }

    @Override
    public void deleteStudent(StudentModel student){
        User studentToDelete = getUserById(student.getId());

        if (!studentToDelete.getEnrolledCourses().isEmpty()){
            studentCourseAssociationRepository.deleteAll(studentToDelete.getEnrolledCourses());
            studentCourseAssociationRepository.flush();
        }

        userRepository.delete(studentToDelete);
    }

    @Override
    public void createNewStudent(StudentModel studentModel, String rawPassword) {
        studentModel.setId(null);
        User studentToInsert = dtoService.convertStudentModelToUser(studentModel);

        studentToInsert.setPassword(bCryptRawPassword(rawPassword));

        userRepository.save(studentToInsert);
    }

    @Override
    public void createNewTeacher(TeacherModel teacherModel, String rawPassword) {
        teacherModel.setId(null);
        User teacherToInsert = dtoService.convertTeacherModelToUser(teacherModel);

        teacherToInsert.setPassword(bCryptRawPassword(rawPassword));

        userRepository.save(teacherToInsert);
    }

    @Override
    public StudentModel getStudentModelById(Integer id) {
        User userFound = userRepository.findById(id).orElse(null);
        if (userFound!=null && userFound.getRole().getId().equals(2)){
            return dtoService.convertUserToStudentModel(userFound);
        }
        return null;
    }

    @Override
    public List<TeacherModel> customSearchTeachers(String searchValue) {
        searchValue = "%" + searchValue + "%";
        List<User> userList = userRepository.findTeachersWithCustomSearch(searchValue);

        List<TeacherModel> teacherModelList = new ArrayList<>();
        for (User user: userList){
            teacherModelList.add(dtoService.convertUserToTeacherModel(user));
        }

        return teacherModelList;
    }


    @Override
    public List<StudentModel> customSearchStudents(String searchValue) {
        searchValue = "%" + searchValue + "%";
        List<User> userList = userRepository.findStudentsWithCustomSearch(searchValue);

        List<StudentModel> studentModelList = new ArrayList<>();
        for (User user: userList){
            studentModelList.add(dtoService.convertUserToStudentModel(user));
        }

        return studentModelList;
    }

    private String bCryptRawPassword(String rawPass){
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        return encoder.encode(rawPass);
    }
}
