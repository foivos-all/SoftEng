<?php
    require "conn.php";

    $fullname = $_POST["fullname"];

    $query = "select user_id from pharmacists where fullname like '$fullname';";
    $result = mysqli_query($conn, $query);
    $pharmacist = mysqli_fetch_assoc($result);

    if(mysqli_num_rows($result) > 0){
        echo $pharmacist['user_id'];
    } else {
        echo "Something went wrong!";
    }
?>