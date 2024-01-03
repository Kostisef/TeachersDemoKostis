function confirmTeacherDeletion(){
    var teacherId = $('#mdl-delete-id').val();

    $.ajax({
        url: '/teachersDemoKostis/deleteTeacher',
        type: 'POST',
        data: { 'teacherId': teacherId },
        success: function(teacher) {
            location.reload();
        },
        error: function() {
            console.error('Error deleting teacher.');
        }
    });
}

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
            console.error('Error fetching teacher information.');
        }
    });
}
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

            openModal('editTeacherModal');
        },
        error: function() {
            // Handle errors if needed
            console.error('Error fetching teacher information.');
        }
    });
}

function openModal(modalId) {
    document.getElementById(modalId).style.display = 'block';
}

function closeModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
}