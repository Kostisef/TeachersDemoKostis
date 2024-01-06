// MODAL: EDIT TEACHER
function openEditTeacherModal(teacherId){

    $.ajax({
        url: '/teachersDemoKostis/getTeacher?id='+teacherId,
        type: 'GET',
        dataType: 'json',
        success: function(teacher) {

            $('#mdl-edit-teacher-id').val(teacher.id);
            // $('#mdl-edit-teacher-id2').val(teacher.id);
            $('#mdl-edit-teacher-firstname').val(teacher.firstname);
            $('#mdl-edit-teacher-lastname').val(teacher.lastname);
            $('#mdl-edit-teacher-username').val(teacher.username);
            $('#mdl-edit-teacher-email').val(teacher.email);
            $('#mdl-edit-teacher-startYear').val(teacher.startYear);

            openModal('editTeacherModal');
        },
        error: function() {
            console.error('Error fetching teacher information.');
        }
    });
}

// MODAL: DELETE TEACHER
function openDeleteTeacherModal(teacherId){
    $.ajax({
        url: '/teachersDemoKostis/getTeacher?id='+teacherId,
        type: 'GET',
        dataType: 'json',
        success: function(teacher) {
            $('#mdl-delete-teacher-id').val(teacher.id);
            openModal('deleteTeacherModal');
        },
        error: function() {
            console.error('Error fetching Teacher information.');
        }
    });
}

// MODAL: EDIT TEACHER
function openShowTeachingCoursesModal(teacherId){

    $.ajax({
        url: '/teachersDemoKostis/getTeacher?id='+teacherId,
        type: 'GET',
        dataType: 'json',
        success: function(teacher) {
            console.log(teacher);
            $('#mdl-showTeachingCourse-teacher-id').val(teacher.id);
            // const fullName = teacher.lastname + " " + teacher.firstname;
            $('#mdl-showTeachingCourse-teacher-fullName').text(teacher.lastname + " " + teacher.firstname);


            const teachingCourses = teacher.taughtCourses;
            var myTableBody = $('#teachingCoursesTableBody');
            myTableBody.empty();

            for (let i = 0; i < teachingCourses.length; i++) {
                const course = teachingCourses[i];
                const row = '<tr>' +
                    '<td>' + course.id + '</td>' +
                    '<td>' + course.name + '</td>' +
                    '<td>' + course.description + '</td>' +
                    '<td>' + course.semester + '</td>' +
                    '<td>' + course.studentAssociations.length + '</td>' +
                    '<td style="text-align: center;">' +
                    '<button title="Remove Teaching Course" data-id="' + course.id + '" type="button" ' +
                    'onclick="openDeleteTeachingCourseModal(' + teacher.id + ', ' + course.id + ')">' +
                    '<i class="fa-solid fa-xmark"></i>' +
                    '</button>' +
                    '</td>' +
                    '</tr>';
                myTableBody.append(row);
            }

            if (teachingCourses.length === 0) {
                $('#teachingCoursesTableBody').append('<tr><td colspan="6">No records found</td></tr>');
            }

            $('#teacher-info').text("Teaching Courses (Teacher: "+ teacher.lastname + " " + teacher.firstname + ")");
            $('#table-records-showTeachingRecords-info').text('Teaching Courses List (Total records: ' + teachingCourses.length + ')');

            openModal('showTeachingCoursesModal');
        },
        error: function() {
            console.error('Error fetching teacher information.');
        }
    });
}

// MODAL: REMOVE COURSE FROM TEACHER
function openDeleteTeachingCourseModal(teacherId, courseId){
    $.ajax({
        url: '/teachersDemoKostis/getTeacher?id='+teacherId,
        type: 'GET',
        dataType: 'json',
        success: function(teacher) {

            $('#mdl-deleteTeachingCourse-teacher-id').val(teacher.id);
            $('#mdl-deleteTeachingCourse-course-id').val(courseId);

            openModal('deleteTeachingCourseModal');
        },
        error: function () {
            console.error('Error fetching Teacher information.');
        }
    });
}


// MODAL: EDIT STUDENT
function openEditStudentModal(studentId){

    $.ajax({
        url: '/teachersDemoKostis/getStudent?id='+studentId,
        type: 'GET',
        dataType: 'json',
        success: function(student) {
            $('#mdl-edit-student-id').val(student.id);
            $('#mdl-edit-student-firstname').val(student.firstname);
            $('#mdl-edit-student-lastname').val(student.lastname);
            $('#mdl-edit-student-username').val(student.username);
            $('#mdl-edit-student-email').val(student.email);
            $('#mdl-edit-student-startYear').val(student.startYear);
            $('#mdl-edit-student-semester').val(student.semester);

            openModal('editStudentModal');
        },
        error: function() {
            console.error('Error fetching student information.');
        }
    });
}

// MODAL: DELETE STUDENT
function openDeleteStudentModal(studentId){
    $.ajax({
        url: '/teachersDemoKostis/getStudent?id='+studentId,
        type: 'GET',
        dataType: 'json',
        success: function(student) {
            $('#mdl-delete-student-id').val(student.id);

            openModal('deleteStudentModal');
        },
        error: function() {
            console.error('Error fetching Student information.');
        }
    });
}

// MODAL: EDIT COURSE
function openEditCourseModal(courseId){

    $.ajax({
        url: '/teachersDemoKostis/getCourse?id='+courseId,
        type: 'GET',
        dataType: 'json',
        success: function(course) {
            $('#mdl-edit-course-id').val(course.id);
            $('#mdl-edit-course-name').val(course.name);
            $('#mdl-edit-course-description').val(course.description);
            $('#mdl-edit-course-semester').val(course.semester);

            openModal('editCourseModal');
        },
        error: function() {
            console.error('Error fetching course information.');
        }
    });
}

// MODAL: DELETE COURSE
function openDeleteCourseModal(courseId){
    $.ajax({
        url: '/teachersDemoKostis/getCourse?id='+courseId,
        type: 'GET',
        dataType: 'json',
        success: function(course) {
            $('#mdl-delete-course-id').val(course.id);

            openModal('deleteCourseModal');
        },
        error: function() {
            console.error('Error fetching Course information.');
        }
    });
}


// MODAL: EDIT ROLE
function openEditRoleModal(roleId){

    $.ajax({
        url: '/teachersDemoKostis/getRole?id='+roleId,
        type: 'GET',
        dataType: 'json',
        success: function(role) {
            $('#mdl-edit-role-id').val(role.id);
            $('#mdl-edit-role-name').val(role.name);
            $('#mdl-edit-role-description').val(role.description);

            openModal('editRoleModal');
        },
        error: function() {
            console.error('Error fetching role information.');
        }
    });
}

// MODAL: ADD TEACHING COURSE TO TEACHER
function openAddTeachingCourseModal(){
    const teacherId = $('#mdl-showTeachingCourse-teacher-id').prop('value');
    console.log("Teacher ID: " + teacherId);
    $.ajax({
        url: '/teachersDemoKostis/getTeacher?id='+teacherId,
        type: 'GET',
        dataType: 'json',
        success: function(teacher) {
            $('#mdl-addTeachingCourse-teacher-id').val(teacher.id);

            openModal('addTeachingCourseModal');
        },
        error: function() {
            console.error('Error fetching role information.');
        }
    });
}



function triggerGrowlModal(msg){
    console.log("triggerGrowlModal() called with msg: "+ msg);
    document.getElementById('growl-msg').innerHTML = msg;

    // Show the modal
    openModal('growlModal');

    // Timeout to close the modal after 1.5 second
    setTimeout(function () {
        const modal = document.querySelector('.custom-message-modal');
        modal.classList.add('fade-out');

        // Timeout to play modal's fade-out effect for 0.5 second
        setTimeout(function () {
            modal.classList.remove('fade-out');
            closeModal('growlModal');
        }, 500);
    }, 1500);
}


function openModal(modalId) {
    document.getElementById(modalId).style.display = 'block';
}

function closeModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
}


function updateTeachingCourseDetails() {
    const courseId = document.getElementById("mdl-addTeachingCourse-course-id").value;

    $.ajax({
        url: '/teachersDemoKostis/getCourse?id='+courseId,
        type: 'GET',
        dataType: 'json',
        success: function(course) {

            $('#mdl-addTeachingCourse-selected-course-id').val(course.id);
            $('#mdl-addTeachingCourse-course-name').val(course.name);
            $('#mdl-addTeachingCourse-course-description').val(course.description);
            $('#mdl-addTeachingCourse-course-semester').val(course.semester);
        },
        error: function() {
            console.error('Error fetching course information.');
        }
    });

}