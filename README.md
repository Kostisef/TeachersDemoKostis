<link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.1/css/all.css" rel="stylesheet" type="text/css" />

<div style="text-align: center">
    <h1>TeachersDemoKostis</h1>
    <a title="TeachersDemoKostis GitHub Repo Link" style="color: white; font-size: 23px; text-decoration: none;" 
        href="https://github.com/Kostisef/TeachersDemoKostis">
        GitHub Repo &zigrarr; <i class="fa-brands fa-github"></i>
    </a>
</div>
<hr>

## (1) Τεχνολογίες που χρησιμοποιήθηκαν: </h2>
- <b>Back-end: </b> Spring MVC, Spring Security
- <b>Front-end: </b> Thymeleaf, HTML, CSS, JS, JQuery
- <b>Database: </b> H2 (in Memory SQL DB)


## (2) Λεπτομέρειες βάσης δεδομένων</h2>
> <div style="font-weight: bold">URL: <a href="http://localhost:8585/teachersDemoKostis/h2-console/">TeachersDemoKostis H2 Database Link</a></div>

#### H2 Credentials (in memory)
| Properties    | Values                 |
|---------------|------------------------|
| Setting Name	 | Generic H2 (Embedded)  |
| Driver Class  | org.h2.Driver          |
| JDBC URL      | jdbc:h2:mem:teachersDB |
| User Name     | sa                     |
| Password      | root                   |


<div>Χρησιμοποιήθηκε ως βάση η H2, έτσι ώστε να μπορεί να εκτελεστεί η εφαρμογή, χωρίς την ανάγκη ύπαρξης προγράμματος MySQL.
Μετά από κάθε εκτέλεση του προγράμματος, το σχήμα καταστρέφεται και δημιουργείται ξανά (spring.jpa.hibernate.ddl-auto=create-drop), στο οποίο φορτώνονται και ορισμένα
αρχικά δεδομένα του αρχείου <span style="font-style: italic">"/resources/data.sql"</span>.</div>

## (3) Ανάλυση Οντοτήτων Σχήματος
- **User:** Περιέχει τις πληροφορίες του χρήστη και διαχωρίζονται (έχουν τιμή **null**) τα διαφορετικά χαρακτηριστικά (_attributes_) ανά ρόλο χρήστη:
  - **_TEACHER_**: Μπορεί να διδάξει **_1 Course_**
  - **_STUDENT_**: Μπορεί να παρακολουθήσει **_N Courses_**
- **Course:** Περιέχει όλες τις σχετικές πληροφορίες σχετικά με τα μαθήματα που διδάσκονται. Ένα Course διδάσκεται από 1 μόνο User _[Ρόλος: TEACHER]_ και μπορεί να παρακολουθείται από N User _[Ρόλος: STUDENT]_ 
- **Role:** Περιέχει τις πληροφορίες σχετικά με το Ρόλο του USER.
- **StudentCourseAssociation:** Είναι ο πίνακας που περιέχει τις συσχετίσεις των ξένων κλειδιών μεταξύ του **_Course_** και του **_User [Ρόλος: STUDENT]_**, καθώς οι συσχέτισή τους είναι _N...N_.

## (4) Λειτουργίες που υποστηρίζονται</h2>

- Για τις CRUD λειτουργίες όλα τα δεδομένα εμφανίζεται με αναδυόμενα block modals</li>
- Εμφανίζονται κατάλληλα ενημερωτικά μηνύματα τόσο σε μορφή static, όσο και σε αναδυόμενα σύντομα modals (info & error messages)
- Όλες οι ενέργειες πραγματοποιούνται από την αρχική σελίδα <span style="font-style: italic; color: #00c7e3">"/dashboard"</span>
- Live search και update των δεδομένων σε κάθε πίνακα (Teacher, Student, Course, Role) της αρχικής σελίδας
- Για τον User [Ρόλος: TEACHER], παρέχονται οι Search, CRUD ενέργειες στο μάθημα που διδάσκει ο κάθε καθηγητής.
- Για τον User [Ρόλος: STUDENT], παρέχονται οι Search, CRUD ενέργειες στα μαθήματα που παρακολουθεί ο κάθε μαθητής.
- Εμφανίζονται custom error pages για τα παρακάτω status codes:
  - Error 401 &rArr; <span style="font-style: italic; color: #00c7e3">/error-401</span>
  - Error 404 &rArr; <span style="font-style: italic; color: #00c7e3">/error-404</span>
  - Error 500 &rArr; <span style="font-style: italic; color: #00c7e3">/error-500</span>
  - Αντί του κλασσικού Whitelabel Error Page για τα generic error &rArr; <span style="font-style: italic; color: #00c7e3">/error</span>

## (5) Λειτουργίες που <span style="text-decoration: underline;">ΔΕΝ</span> υποστηρίζονται</h2>

- Η διαγραφή ενός ρόλου, καθώς και η μετονομασία του ονόματος του Ρόλου (επιτρέπεται μόνο η επεξεργασία της περιγραφής του Ρόλου).
- Η αλλαγή του κωδικού πρόσβασης του χρήστη.
- Οι ελεγχόμενες ενέργειες/προσβάσεις στις σελίδες, ανάλογα τον ρόλο του χρήστη.
- Σελιδοποιημένα αποτελέσματα στα POST/GET requests και εμφάνισή τους με αντίστοιχο τρόπο στο front-end. Σε μεγαλύτερη βάση θα ήταν αναπόφευκτα μία από τις βασικές λειτουργίες της εφαρμογής.

## (6) Πρόσβαση στην εφαρμογή</h2>
> <div style="font-weight: bold">URL: <a href="http://localhost:8585/teachersDemoKostis/login">TeachersDemoKostis Link</a></div>
<div>Για την πρόσβαση στην εφαρμογή απαιτούνται διαπιστευτήρια χρήστη. Ο χρήστης αν εισάγει επιτυχές email και password,
τότε ανακατευθύνεται αυτομάτως στην αρχική σελίδα <span style="font-style: italic; color: #00c7e3">"/dashboard"</span> της εφαρμογής.</div>

> Σημείωση: Οι κωδικοί χρήστη αποθηκεύονται στη βάση δεδομένων κρυπτογραφημένοι με τον BCrypt αλγόριθμο.

#### User Login Credentials
| # |       Email       | Password |  Role   |
|:-:|:-----------------:|:--------:|:-------:|
| 1 | teacher@test.com  |  admin   | TEACHER |
| 2 | teacher2@test.com |  admin   | TEACHER |
| 3 | student@test.com  |  admin   | STUDENT |
| 4 | student2@test.com |  admin   | STUDENT |