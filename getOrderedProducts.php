<?php
    require "conn.php";

    $order_id = $_POST['order_id'];
    $query = "select products.name, orderedproducts.stock, orderedproducts.price from orderedproducts
                inner join products on orderedproducts.product_id = products.id where order_id='$order_id';";
    $result = mysqli_query($conn, $query);

    $data = array();
    while($row = mysqli_fetch_array($result)){
        array_push($data, $row);
    }

    echo json_encode($data);
?>