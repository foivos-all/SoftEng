<?php
    require "conn.php";

    $query = "select * from users order by id desc;";
    $result = mysqli_query($conn, $query); 
    $last_user = mysqli_fetch_assoc($result);

    $user_id = $last_user['id'];
    $fullname = $_POST["fullname"];
    $age = $_POST["age"];
    $gender = $_POST["gender"];
    $address = $_POST["address"];
    $phone = $_POST["phone"];
    $info = $_POST["info"];
    $query = "insert into patients values($user_id, '$fullname', $age, '$gender', '$address', '$phone', '$info');";
    $result = mysqli_query($conn, $query);

    echo "back_to_login";
?>