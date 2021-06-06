<?php
    require "conn.php";

    $doctor_id = $_POST['doctor_id'];
    $hours = $_POST["hours"];

    $query = "select * from doctorHours where doctor_id = '$doctor_id';";
    $result = mysqli_query($conn, $query);

    if (mysqli_num_rows($result)==0)
        $query = "INSERT INTO doctorHours VALUES('$doctor_id', '$hours');";
    else 
        $query = "UPDATE doctorHours SET hours = '$hours' WHERE doctor_id = '$doctor_id';";
    
    $result = mysqli_query($conn, $query);

    echo "Success";
?>