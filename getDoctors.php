<?php
    require "conn.php";

    $query = "select user_id, fullname from doctors;";
    $result = mysqli_query($conn, $query);

    $data = array();
    while($row = mysqli_fetch_array($result)){
        array_push($data, $row);
    }

    echo json_encode($data);
?>