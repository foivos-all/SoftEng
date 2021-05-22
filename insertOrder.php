<?php
    require "conn.php";

    $user_id = $_POST['user_id'];
    $pharmacist_id = $_POST["pharmacist_id"];
    $query = "INSERT INTO orders VALUES(null, '$user_id', '$pharmacist_id', CURDATE(), 'P');";
    $result = mysqli_query($conn, $query);

    echo "Success";
?>