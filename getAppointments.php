<?php
    require "conn.php";

    $doctor_id = $_POST['doctor_id'];
    $query = "select id, fullname, date_hour from doctorsAppointment inner join patients on patient_id = user_id where doctor_id = '$doctor_id' AND state = 'P';";
    $result = mysqli_query($conn, $query);

    $data = array();
    while($row = mysqli_fetch_array($result)){
        array_push($data, $row);
    }

    echo json_encode($data);
?>