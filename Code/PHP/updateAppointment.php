<?php
    require "conn.php";

    $appointment_id = $_POST['appointment_id'];
    $status = $_POST["status"];
    $query = "update doctorsAppointment set state = '$status' where id = '$appointment_id';";
    $result = mysqli_query($conn, $query);

    echo "Success";
?>