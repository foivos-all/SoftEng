<?php
    session_start();

    require "conn.php";
    $email = $_POST["email"];
    $pass = $_POST["pass"];

    $query = "select * from users where email like '$email' and password like '$pass';";
    $result = mysqli_query($conn, $query);
    $user = mysqli_fetch_assoc($result);

    if(mysqli_num_rows($result) > 0){
        $_SESSION['userid'] = $user['id'];
        echo $user['property'];
    } else {
        echo "Something went wrong!";
    }
?>