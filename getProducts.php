<?php
    require "conn.php";


    $pharmacist_id = $_POST["pharmacist_id"];
    $query = "select name, stock, price from products where pharmacist_id = '$pharmacist_id';";
    $result = mysqli_query($conn, $query);

    $data = array();
    while($row = mysqli_fetch_array($result)){
        array_push($data, $row);
    }

    echo json_encode($data);
?>