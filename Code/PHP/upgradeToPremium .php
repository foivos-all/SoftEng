<?php
    require "conn.php";

    $user_id = $_POST['user_id'];
    $query = "update users set property = 'P' where id = '$user_id';";
    $result = mysqli_query($conn, $query);

    echo "Success";
?>