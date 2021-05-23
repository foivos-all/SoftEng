<?php
    require "conn.php";

    $pharmacist_id = $_POST["pharmacist_id"];
    $name = $_POST["name"];
    $stock = $_POST["stock"];
    $price = $_POST["price"];

    $query = "select id from products where pharmacist_id = '$pharmacist_id' AND name = '$name';";
    $result = mysqli_query($conn, $query);
    $product = mysqli_fetch_assoc($result);
    $product_id = $product["id"];

    $query = "select * from orders order by id desc limit 1;";
    $result = mysqli_query($conn, $query);
    $order = mysqli_fetch_assoc($result);
    $order_id = $order["id"];

    $query = "INSERT INTO orderedProducts VALUES('$order_id', '$product_id', '$stock', '$price');";
    $result = mysqli_query($conn, $query);

    echo "Success";
?>