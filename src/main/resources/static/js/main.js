// Teacher
function openDeleteTeacherModal(teacherId){
    $.ajax({
        url: '/teachersDemoKostis/getTeacher?id='+teacherId,
        type: 'GET',
        dataType: 'json',
        success: function(teacher) {
            $('#mdl-delete-id').val(teacher.id);
            openModal('deleteTeacherModal');
        },
        error: function() {
            // Handle errors if needed
            console.error('Error fetching Teacher information.');
        }
    });
}

// Student
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

// Teacher
function openEditTeacherModal(teacherId){

    $.ajax({
        url: '/teachersDemoKostis/getTeacher?id='+teacherId,
        type: 'GET',
        dataType: 'json',
        success: function(teacher) {
            // Populate the modal form with the fetched data
            $('#mdl-id').val(teacher.id);
            $('#mdl-firstname').val(teacher.firstname);
            $('#mdl-lastname').val(teacher.lastname);
            $('#mdl-username').val(teacher.username);
            $('#mdl-email').val(teacher.email);
            $('#mdl-startYear').val(teacher.startYear);

            openModal('editTeacherModal');
        },
        error: function() {
            // Handle errors if needed
            console.error('Error fetching teacher information.');
        }
    });
}

// Student
function openEditStudentModal(studentId){

    $.ajax({
        url: '/teachersDemoKostis/getStudent?id='+studentId,
        type: 'GET',
        dataType: 'json',
        success: function(student) {
            // Populate the modal form with the fetched data
            $('#mdl-student-id').val(student.id);
            $('#mdl-student-firstname').val(student.firstname);
            $('#mdl-student-lastname').val(student.lastname);
            $('#mdl-student-username').val(student.username);
            $('#mdl-student-email').val(student.email);
            $('#mdl-student-startYear').val(student.startYear);
            $('#mdl-student-semester').val(student.semester);

            openModal('editStudentModal');
        },
        error: function() {
            console.error('Error fetching teacher information.');
        }
    });
}

function triggerGrowlModal(msg){
    console.log("triggerGrowlModal() called with msg: "+ msg);
    document.getElementById('growl-msg').innerHTML = msg;

    // Show the modal
    openModal('growlModal');

    // Set a timeout to close the modal after 5 seconds
    setTimeout(function () {
        const modal = document.querySelector('.custom-message-modal');
        modal.classList.add('fade-out');
        setTimeout(function () {
            modal.classList.remove('fade-out');
            closeModal('growlModal');
        }, 500);
    }, 2000);
}


function openModal(modalId) {
    document.getElementById(modalId).style.display = 'block';
}

function closeModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
}