<?php
    require "conn.php";

    $query = "select * from users order by id desc;";
    $result = mysqli_query($conn, $query); 
    $last_user = mysqli_fetch_assoc($result);

    $user_id = $last_user['id'];
    $fullname = $_POST["fullname"];
    $specialty = $_POST["specialty"];
    $address = $_POST["address"];
    $phone = $_POST["phone"];
    $query = "insert into doctors values($user_id, '$fullname', '$specialty', '$address', '$phone', 0, 0);";
    $result = mysqli_query($conn, $query);

    echo "Registered successfully!";
?>