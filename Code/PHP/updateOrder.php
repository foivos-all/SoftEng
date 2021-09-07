<?php
    require "conn.php";

    $order_id = $_POST['order_id'];
    $status = $_POST["status"];
    $query = "update orders set state = '$status' where id = '$order_id';";
    $result = mysqli_query($conn, $query);

    echo "Success";
?>