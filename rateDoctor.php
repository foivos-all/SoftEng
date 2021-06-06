<?php
    require "conn.php";

    $doctor_id = $_POST['doctor_id'];
    $rating = $_POST['rating'];

    $query = "select rate, rate_count from doctors where user_id = '$doctor_id';";
    $result = mysqli_query($conn, $query);

    while($row = mysqli_fetch_array($result)){
        $old_rating = $row['rate'];
        $rate_count = $row['rate_count'];
    }

    $new_rate_count = $rate_count + 1;
    $query = "update doctors set rate_count = '$new_rate_count' where user_id = '$doctor_id';";
    $result = mysqli_query($conn, $query);

    $new_rating = ($old_rating + $rating) / $new_rate_count;
    $query = "update doctors set rate = '$new_rating' where user_id = '$doctor_id';";
    $result = mysqli_query($conn, $query);

    echo "Success";
?>