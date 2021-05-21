<?php
    require "conn.php";

    $user_id = $_POST['user_id'];
    $productname = $_POST["productname"];
    $stock = $_POST["stock"];
    $price = $_POST["price"];
    $query = "insert into products values(null, '$productname', '$stock', '$price', '$user_id');";
    $result = mysqli_query($conn, $query);
    echo "Products Added!";
?>