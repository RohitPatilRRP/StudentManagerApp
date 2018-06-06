<?php
   define('DB_SERVER', 'localhost');
   define('DB_USERNAME', 'root');
   define('DB_PASSWORD', '');
   define('DB_DATABASE', 'sdg_rait_exam_s/w');
   $db = mysqli_connect(DB_SERVER,DB_USERNAME,DB_PASSWORD,DB_DATABASE);
session_start();
?>

<!DOCTYPE html>
<html>
<head>
<script>
function validateform(){  
var rollno=document.myform.rollno.value;  
var seatno=document.myform.seatno.value; 
var pattern=document.myform.pattern.value;  
var branch=document.myform.branch.value;  
var sem=document.myform.sem.value;  


//var password=document.myform.password.value;  
  
if (rollno==null || rollno.length!=8){  
  alert("Roll no. must be of 8 characters");  
  return false;  
}
if (branch==null || branch==""){  
  alert("Branch must not be blank");  
  return false;  
}
if (sem==null || sem==""){  
  alert("Semester must not be blank");  
  return false;  
}
if (seatno==null || seatno==""){  
  alert("Seat Number must not be blank");  
  return false;  
}
if (pattern==null || pattern==""){  
  alert("Pattern must not be blank");  
  return false;  
}//else if((password.length<6) ||(password.length==' ') ){  
 // alert("Password must be at least 6 characters long.");  
  //return false;  

}  
</script>
</head>

<style>
input[type=text], select {
    width: 50%;
    height:50%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
   font-size: 20px;
}

input[type=password], select {
    width: 50%;
height:50%;
    padding: 12px 20px;
    margin: 8px 0;
    display: inline-block;
    border: 1px solid #ccc;
    border-radius: 4px;
    box-sizing: border-box;
    font-size: 20px;
    
}

input[type=submit] {
    width: 50%;
    background-color: #DC143C;
    color: black;
    padding: 14px 20px;
    margin: 8px 0;
    border: none;
    border-radius: 4px;
    cursor: pointer;
    font-weight: bold;
    font-size: 20px;
}

input[type=submit]:hover {
    background-color: #B22222;
    
}

div {
    width: 50%;
    height: 50%;
    align: center;
    border-radius: 2px;
    background-color: #f2f2f2;
    padding: 20px;
}
.center {
    margin: auto;
    width: 50%;
    border: 3px solid #800000;
    padding: 10px;
    text-align: center;
    font-size: 20px;
   font-weight : bold; 
}
</style>
<body>

<h3 style="text-align: left; color: #8B0000; font-family: Arial; font-size: 36px;">&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp;&nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp; &nbsp;
&nbsp; GET MARKSHEET </h3>

<div class="center">
  <form name="myform" method="POST" onsubmit="return validateform()" >  
Enter Roll No: <input type="text" name="rollno"><br/>  
Seat Number: <input type="text" name="seatno" id="seatno"><br/>
Branch: <input type="text" name="branch" id="branch" ><br/>  
Semester:  <input type="text" name="sem" id="sem"><br/>  
Pattern: <input type="text" name="pattern" id="pattern"><br/>  
<input type="submit" value="Get MARKSHEET" id="LOGIN" name="LOGIN"">  
</form>  
</div>

<div>
<?php
$q = $_POST['sem'];
$z = $_POST['pattern'];
$b= $_POST['branch'];
$s = $_POST['seatno'];
$sel=mysqli_query($db,"select * from course_table_rev where sem='$q' and pattern='$z' and branch='$b'") or die(mysqli_error($db)); ;
if(mysqli_num_rows($sel)>0){
    echo 
"<table width=\"600\" border=\"1\" cellpadding=\"10\" cellspacing=\"1\">
<tr>
<th>Branch</th>
<th>Semester</th>
<th>Pattern</th>
<th>Subject</th>
</tr>";
while ($arr=mysqli_fetch_array($sel)){
echo "<tr>";
echo "<td><a href=\"index.php\">".$arr['branch']."</a></td>";
echo "<td>".$arr['sem']."</td>";
echo "<td>".$arr['pattern']."</td>";
echo "<td>".$arr['subject']."</td>";
echo "</tr>";
}
$_SESSION['seatno']=$s;
$_SESSION['branch']=$b;
$_SESSION['sem']=$q;
$_SESSION['pattern']=$z;
}
else
{
    echo "<h2>No Results Found</h2>";
}
?>
</div>
</body>
</html>



