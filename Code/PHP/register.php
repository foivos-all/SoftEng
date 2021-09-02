<?php
    require "conn.php";
    $email = $_POST["email"];
    $pass = $_POST["pass"];
    $property = $_POST["property"];
    $query = "insert into users values(null, '$email', '$pass', '$property');";
    $result = mysqli_query($conn, $query);

    echo "Registered successfully!";
?>