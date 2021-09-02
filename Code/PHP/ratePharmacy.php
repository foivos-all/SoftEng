<?php
    require "conn.php";

    $pharmacy_id = $_POST['pharmacy_id'];
    $rating = $_POST['rating'];

    $query = "select rate, rate_count from pharmacies where id = '$pharmacy_id';";
    $result = mysqli_query($conn, $query);

    while($row = mysqli_fetch_array($result)){
        $old_rating = $row['rate'];
        $rate_count = $row['rate_count'];
    }

    $new_rate_count = $rate_count + 1;
    $query = "update pharmacies set rate_count = '$new_rate_count' where id = '$pharmacy_id';";
    $result = mysqli_query($conn, $query);

    $new_rating = ($old_rating + $rating) / $new_rate_count;
    $query = "update pharmacies set rate = '$new_rating' where id = '$pharmacy_id';";
    $result = mysqli_query($conn, $query);

    echo "Success";
?>