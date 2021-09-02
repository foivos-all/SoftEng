<?php
    require "conn.php";

    $doctor_id = $_POST['doctor_id'];
    $patient_id = $_POST['patient_id'];
    $hours = $_POST["hours"];

    $query = "INSERT INTO doctorsAppointment VALUES(null, '$doctor_id', '$patient_id', '$hours', 'P');";
    $result = mysqli_query($conn, $query);

    echo "Success";
?>