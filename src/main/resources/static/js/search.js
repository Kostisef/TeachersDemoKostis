function searchTeachers(){
    var searchValue = document.getElementById("teacherTableSearch").value;
    console.log("searchTeachers() called with searchValue: "+searchValue);

    $.ajax({
        type: "GET",
        url: "/teachersDemoKostis/searchTeachers",
        data: { searchValue: searchValue },
        success: function (teacherModelList) {
            const myTableBody = $('#teacherTableBody');
            myTableBody.empty();

            if (teacherModelList.length === 0){
                const row = '<tr><td colspan="8">No records found. Search something else!</td></tr>';
                myTableBody.append(row);
            } else {
                teacherModelList.forEach( function (teacher) {
                    const row = '<tr>' +
                        '<td>' + teacher.id + '</td>' +
                        '<td>' + teacher.firstname + '</td>' +
                        '<td>' + teacher.lastname + '</td>' +
                        '<td>' + teacher.username + '</td>' +
                        '<td>' + teacher.email + '</td>' +
                        '<td>' + teacher.startYear + '</td>' +
                        '<td>' + teacher.teachingCourseModelList.length + '</td>' +
                        '<td class="actionColumn">' +
                        '<button title="Show Teaching Courses" data-id="' + teacher.id + '" type="button" ' +
                        'onclick="openShowTeachingCoursesModal(' + teacher.id + ')">' +
                        '<i class="fa-solid fa-person-chalkboard"></i>' +
                        '</button>' +

                        '<button title="Edit Teacher" data-id="' + teacher.id + '" type="button" ' +
                        'onclick="openEditTeacherModal(' + teacher.id + ')">' +
                        '<i class="fa-solid fa-pen"></i>' +
                        '</button>' +

                        '<button title="Delete Teacher" data-id="' + teacher.id + '" type="button" ' +
                        'onclick="openDeleteTeacherModal(' + teacher.id + ')">' +
                        '<i class="fa-solid fa-trash"></i>' +
                        '</button>' +
                        '</td>' +
                        '</tr>';
                    myTableBody.append(row);
                });
            }

        },
        error: function () {
            console.log("Error while fetching search teacher results.");
        }
    });
}


function searchStudents(){
    var searchValue = document.getElementById("studentTableSearch").value;
    console.log("searchStudents() called with searchValue: "+searchValue);

    $.ajax({
        type: "GET",
        url: "/teachersDemoKostis/searchStudents",
        data: { searchValue: searchValue },
        success: function (studentModelList) {
            const myTableBody = $('#studentTableBody');
            myTableBody.empty();

            if (studentModelList.length === 0){
                const row = '<tr><td colspan="9">No records found. Search something else!</td></tr>';
                myTableBody.append(row);
            } else {
                studentModelList.forEach( function (student) {
                    const row = '<tr>' +
                        '<td>' + student.id + '</td>' +
                        '<td>' + student.firstname + '</td>' +
                        '<td>' + student.lastname + '</td>' +
                        '<td>' + student.username + '</td>' +
                        '<td>' + student.email + '</td>' +
                        '<td>' + student.startYear + '</td>' +
                        '<td>' + student.semester + '</td>' +
                        '<td>' + student.courseModelList.length + '</td>' +
                        '<td class="actionColumn">' +
                        '<button title="Show Enrolled Courses" data-id="' + student.id + '" type="button" ' +
                        'onclick="openEnrolledCoursesModal(' + student.id + ')">' +
                        '<i class="fa-brands fa-leanpub"></i>' +
                        '</button>' +

                        '<button title="Edit Student" data-id="' + student.id + '" type="button" ' +
                        'onclick="openEditStudentModal(' + student.id + ')">' +
                        '<i class="fa-solid fa-pen"></i>' +
                        '</button>' +

                        '<button title="Delete Student" data-id="' + student.id + '" type="button" ' +
                        'onclick="openDeleteStudentModal(' + student.id + ')">' +
                        '<i class="fa-solid fa-trash"></i>' +
                        '</button>' +
                        '</td>' +
                        '</tr>';
                    myTableBody.append(row);
                });
            }


        },
        error: function () {
            console.log("Error while fetching search student results.");
        }
    });
}


function searchCourses(){
    var searchValue = document.getElementById("courseTableSearch").value;
    console.log("searchCourses() called with searchValue: "+searchValue);

    $.ajax({
        type: "GET",
        url: "/teachersDemoKostis/searchCourses",
        data: { searchValue: searchValue },
        success: function (courseModelList) {
            const myTableBody = $('#courseTableBody');
            myTableBody.empty();

            if (courseModelList.length === 0){
                const row = '<tr><td colspan="7">No records found. Search something else!</td></tr>';
                myTableBody.append(row);
            } else {
                courseModelList.forEach( function (course) {
                    const row = '<tr>' +
                        '<td>' + course.id + '</td>' +
                        '<td>' + course.name + '</td>' +
                        '<td>' + course.description + '</td>' +
                        '<td>' + course.semester + '</td>' +
                        '<td>' + course.teacherFullName + '</td>' +
                        '<td>' + course.numOfStudents + '</td>' +
                        '<td class="actionColumn">' +

                        '<button title="Edit Course" data-id="' + course.id + '" type="button" ' +
                        'onclick="openEditCourseModal(' + course.id + ')">' +
                        '<i class="fa-solid fa-pen"></i>' +
                        '</button>' +

                        '<button title="Delete Course" data-id="' + course.id + '" type="button" ' +
                        'onclick="openDeleteCourseModal(' + course.id + ')">' +
                        '<i class="fa-solid fa-trash"></i>' +
                        '</button>' +
                        '</td>' +
                        '</tr>';
                    myTableBody.append(row);
                });
            }


        },
        error: function () {
            console.log("Error while fetching search student results.");
        }
    });
}


function searchRoles(){
    var searchValue = document.getElementById("roleTableSearch").value;
    console.log("searchRoles() called with searchValue: "+searchValue);

    $.ajax({
        type: "GET",
        url: "/teachersDemoKostis/searchRoles",
        data: { searchValue: searchValue },
        success: function (roleList) {
            const myTableBody = $('#roleTableBody');
            myTableBody.empty();

            if (roleList.length === 0){
                const row = '<tr><td colspan="4">No records found. Search something else!</td></tr>';
                myTableBody.append(row);
            } else {
                roleList.forEach( function (role) {
                    const row = '<tr>' +
                        '<td>' + role.id + '</td>' +
                        '<td>' + role.name + '</td>' +
                        '<td>' + role.description + '</td>' +
                        '<td class="actionColumn">' +

                        '<button title="Edit Role" data-id="' + role.id + '" type="button" ' +
                        'onclick="openEditRoleModal(' + role.id + ')">' +
                        '<i class="fa-solid fa-pen"></i>' +
                        '</button>' +
                        '</td>' +
                        '</tr>';
                    myTableBody.append(row);
                });
            }


        },
        error: function () {
            console.log("Error while fetching search role results.");
        }
    });
}