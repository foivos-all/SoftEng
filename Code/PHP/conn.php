<?php
    session_start();
    
    $db_name = "healthcare";
    $username = "root";
    $password = "";
    $server_name = "localhost";

    $conn = mysqli_connect($server_name, $username, $password, $db_name);
?>