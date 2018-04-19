<%--
  User: Harshit Sharma
  Date: 23/1/18
  Time: 10:53 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="com.onlinedealfinder.model.C" %>
<%@ page import="org.bson.Document" %>
<%@ page import="com.onlinedealfinder.model.MMongo" %>
<%@ page import="com.onlinedealfinder.model.DB" %>
<%@ page import="java.util.Iterator" %>
<%@ page import="com.mongodb.client.FindIterable" %>
<%@ page import="javax.print.Doc" %>
<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>OnlineDealFinder</title>

    <!-- Bootstrap core CSS -->
    <link href="vendor/bootstrap/css/bootstrap.min.css" rel="stylesheet">
    <link href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css" rel="stylesheet"/>
    <!-- Custom fonts for this template -->
    <link href="https://fonts.googleapis.com/css?family=Catamaran:100,200,300,400,500,600,700,800,900" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Lato:100,100i,300,300i,400,400i,700,700i,900,900i"
          rel="stylesheet">

    <!-- Custom styles for this template -->
    <link href="css/online-deal-finder.min.css" rel="stylesheet">
	<script src="https://apis.google.com/js/platform.js" async defer></script>
	<meta name="google-signin-client_id" content="494081324513-l3br43frqkrjet8aeq6u0eq1avmou01b.apps.googleusercontent.com">
</head>
<body>

<%
boolean flag = false;
String valuecookie="";
Cookie[] cookies = request.getCookies();

if (cookies != null) {
	for (int i = 0; i < cookies.length; i++) {
		String namecookie = cookies[i].getName();
		valuecookie = cookies[i].getValue();
		if (namecookie.equals(C.COOKIE.LOGIN_COOKIE)) {
			cookies[i].setMaxAge(3*60*60);
			response.addCookie(cookies[i]);	
			flag=true;
			break;
		}
		if (i == (cookies.length - 1))
		{
			flag = false;
			break;
		}
	}
} else {
	flag = false;
}

if(!flag) {
%>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark navbar-custom fixed-top">
    <div class="container">
        <a class="navbar-brand" href="/OnlineDealFinder/index.jsp">Deal Finder</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        
        <div class="collapse navbar-collapse" id="navbarResponsive">
        	<!-- ul class="navbar-nav mr-auto">
        	<li class="nav-item">
            	<form class="form-inline my-2 my-lg-0" id="search-bar-custom">
			      <input id="srch-area" class="form-control" type="text" placeholder="Search">
			      <button class="btn btn-primary my-2 my-sm-0" type="submit"><i class="fa fa-search"></i></button>
		  		</form>
		  		</li>
        	</ul -->
            <ul class="navbar-nav ml-auto">
            	
                <li class="nav-item">
                    <a class="nav-link"  data-toggle="modal" data-target="#myModal" href="#">Login <span class="amp">&amp;</span> Signup</a>
                </li>
            </ul>
        </div>
    </div>
</nav>

<header class="masthead text-center text-white">
    <div class="masthead-content">
        <div class="col-lg-12">
            <h1 class="masthead-heading mb-0">Online Deal Finder</h1>
            <h2 class="masthead-subheading mb-0"></h2>
            <a href="#" class="btn btn-primary btn-xl rounded-pill mt-5">Learn More</a>
        </div>
    </div>
    <div class="bg-circle-1 bg-circle"></div>
    <div class="bg-circle-2 bg-circle"></div>
    <div class="bg-circle-3 bg-circle"></div>
    <div class="bg-circle-4 bg-circle"></div>
</header>
<img id="myImg"><br>
<p id="name"></p> 

  
<div id="status">


</div>
<script>

    var longi;
    var latit;
    (function getLocation() {
            if (navigator.geolocation) {
                navigator.geolocation.watchPosition(function(position) {
                    longi = position.coords.longitude;
                    latit = position.coords.latitude;
                    document.getElementById('latit').value = latit;
                    document.getElementById('longi').value = longi;

                });
            } else {
                alert("Geolocation is not supported by this browser.");
            }
        }
    )();
			function onSignIn(googleUser) {

				  var profile = googleUser.getBasicProfile();
				  var imagurl=profile.getImageUrl();
				  var name=profile.getName();
				  var email=profile.getEmail();

				  gapi.auth2.getAuthInstance().disconnect();
				  
			      var redirectUrl = 'loginOAuth';
			      //using jquery to post data dynamically
			      var form = $('<form action="' + redirectUrl + '" method="post">' +
			                          '<input type="text" name="u_name" value="' + name + '" />' +
			                          '<input type="text" name="u_email" value="' + email + '" />' +
			                          '<input type="text" name="u_imgurl" value="' + imagurl + '" />' +
                                      '<input type="hidden" name="u_longi" value="' + longi + '" />' +
                                      '<input type="hidden" name="u_lati" value="' + latit + '" />' +
			                   '</form>');
			    $('body').append(form);
			    form.submit();
			 }
</script>
<section>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-6 order-lg-2">
                <div class="p-5">
                    <img class="img-fluid rounded-circle" src="img/01.jpg" alt="">
                </div>
            </div>
            <div class="col-lg-6 order-lg-1">
                <div class="p-5">
                    <h2 class="display-4">For those about to rock...</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod aliquid, mollitia odio veniam sit
                        iste esse assumenda amet aperiam exercitationem, ea animi blanditiis recusandae! Ratione
                        voluptatum molestiae adipisci, beatae obcaecati.</p>
                </div>
            </div>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-6">
                <div class="p-5">
                    <img class="img-fluid rounded-circle" src="img/02.jpg" alt="">
                </div>
            </div>
            <div class="col-lg-6">
                <div class="p-5">
                    <h2 class="display-4">We salute you!</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod aliquid, mollitia odio veniam sit
                        iste esse assumenda amet aperiam exercitationem, ea animi blanditiis recusandae! Ratione
                        voluptatum molestiae adipisci, beatae obcaecati.</p>
                </div>
            </div>
        </div>
    </div>
</section>

<section>
    <div class="container">
        <div class="row align-items-center">
            <div class="col-lg-6 order-lg-2">
                <div class="p-5">
                    <img class="img-fluid rounded-circle" src="img/03.jpg" alt="">
                </div>
            </div>
            <div class="col-lg-6 order-lg-1">
                <div class="p-5">
                    <h2 class="display-4">Let there be rock!</h2>
                    <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Quod aliquid, mollitia odio veniam sit
                        iste esse assumenda amet aperiam exercitationem, ea animi blanditiis recusandae! Ratione
                        voluptatum molestiae adipisci, beatae obcaecati.</p>
                </div>
            </div>
        </div>
    </div>
</section>
<!-- Large modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myLargeModalLabel"
    aria-hidden="true">
    <div class="modal-dialog modal-lg">
        <div class="modal-content">
            <div class="modal-header">
                
                <h4 class="modal-title" id="myModalLabel">Login/Registration</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                    ×
                    </button>
            </div>
            <div class="modal-body">
                <div class="row">
                    <div class="col-md-8" style="border-right: 1px dotted #C2C2C2;padding-right: 30px;">
                        <!-- Nav tabs -->
                        <ul class="nav nav-tabs">
                            <li class="nav-item">
                            	<a class="nav-link active" href="#Login" data-toggle="tab">Login</a>
                            </li>
                            <li class="nav-item">
                            	<a class="nav-link" href="#Registration" data-toggle="tab">Registration</a>
                            </li>
                        </ul>
                        <!-- Tab panes -->
                        <div class="tab-content">
                          <div class="tab-pane active" id="Login">
                                <form role="form" action="login" method="post"  class="form-horizontal">
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">
                                        Email</label>
                                    	<div class="col-sm-10">
											<input type="email" name="form-email" aria-describedby="emailHelp" placeholder="Email..." class="form-email form-control" id="form-email" required="">
										</div>
                                </div>
                                <div class="form-group">
                                    <label for="exampleInputPassword1" class="col-sm-2 control-label">
                                        Password</label>
                                    <div class="col-sm-10">
										<input type="password" name="form-password" placeholder="Password..." class="form-password form-control" id="form-password" required="">
									</div>
									
                                </div>
                                    <input type="hidden" name="longitude" value="" id="longi"/>
                                    <input type="hidden" name="latitude" value="" id="latit"/>

                                    <div class="row">
                                    <div class="col-sm-2">
                                    </div>
                                    <div class="col-sm-10">
                                        <button type="submit" class="btn btn-primary btn-sm mr-4">
                                            Submit</button>
                                        <a href="javascript:;">Forgot your password?</a>
                                    </div>
                                </div>
                                </form>
                            </div>
                            
                            
                            <div class="tab-pane" id="Registration">
                             <form role="form" action="signup" method="post"  class="form-horizontal">
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">
                                        Name</label>
                                    <div class="col-sm-10">
                                         <input type="text" name="form-first-name" placeholder="Full name..." class="form-first-name form-control" id="form-full-name" required="">
                                    </div>
                                </div>
                                <div class="form-group">
                                    <label for="email" class="col-sm-2 control-label">
                                        Email</label>
                                    <div class="col-sm-10">
                                        <input type="email" name="form-email" aria-describedby="emailHelp" placeholder="Email..." class="form-email form-control" id="form-email" required="">
                                    </div>
                                </div>
                                <!-- div class="form-group">
                                    <label for="mobile" class="col-sm-2 control-label">
                                        Mobile</label>
                                    <div class="col-sm-10">
                                        <input type="text" name="form-number" class="form-control" id="mobile" placeholder="Mobile" />
                                    </div>
                                </div-->
                                <div class="form-group">
                                    <label for="password" class="col-sm-2 control-label">
                                        Password</label>
                                    <div class="col-sm-10">
                                        <input type="password" name="form-password" placeholder="Password..." class="form-last-name form-control" id="form-password">
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-sm-2">
                                    </div>
                                    <div class="col-sm-10">
                                        <button type="submit" class="btn btn-primary btn-sm">
                                            Sign Up
                                        </button>
                                    </div>
                                </div>
                                </form>
                            </div>
                        </div>
                        <div id="OR" class="hidden-xs">
                            OR</div>
                    </div>
                    <div class="col-md-4">
                        <div class="row text-center sign-with">
                            <div class="col-md-12">
                                <h3>
                                    Sign in with</h3>
                            </div>
                            <div class="col-md-12">
                                <div class="btn-group btn-group-justified">
                                    <!-- a href="#" class="btn btn-primary">Facebook</a-->
                                    <div class="g-signin2" data-onsuccess="onSignIn"></div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<% } else{
    Document userDetails = Document.parse(valuecookie);

    String user_name = userDetails.getString(C.FIELD.NAME);
    String user_email = userDetails.getString(C.FIELD.EMAIL);
    String user_imgurl = userDetails.getString(C.FIELD.IMGURL);
    String user_phnno = userDetails.getString(C.FIELD.PHONE);
    String user_longitude = userDetails.getString(C.COOKIE.LONGITUDE_FIELD);
    String user_latitude = userDetails.getString(C.COOKIE.LATITUDE_FIELD);

    String editProfile = request.getParameter("editProfile");
    String sellitem = request.getParameter("sellItem");
    String history = request.getParameter("history");


    if(editProfile==null)
        editProfile = "";
    if(sellitem == null)
        sellitem = "";
    if(history == null)
        history ="";

    user_email = user_email == null?"":user_email;
    user_name = user_name == null?"":user_name;
    user_imgurl = user_imgurl == null?"":user_imgurl;
    user_phnno = user_phnno == null?"":user_phnno;
%>

<!-- Navigation -->
<nav class="navbar navbar-expand-lg navbar-dark navbar-custom fixed-top">
    <div class="container">
        <a class="navbar-brand mr-5" href="#">Deal Finder</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarResponsive"
                aria-controls="navbarResponsive" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        
        <div class="collapse navbar-collapse" id="navbarResponsive">
        	<ul class="navbar-nav mr-auto">
        	<li class="nav-item">
            	<form class="form-inline my-2 my-lg-0" id="search-bar-custom">
            		<div class="btn-group">
					  <button type="button" class="btn btn-primary">Filter</button>
					  <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split filter-button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
						    <span class="sr-only">Toggle Dropdown</span>
					  </button>
					  <div class="dropdown-menu">

                          <a href="index.jsp?category=<%=C.CATEGORY.ELECTRONICS%>" class="dropdown-item">Electronics</a>
                          <a href="index.jsp?category=<%=C.CATEGORY.HOMEFURNITURE%>" class="dropdown-item">Home & Furniture</a>
                          <a href="index.jsp?category=<%=C.CATEGORY.TVAPPLIANCES%>" class="dropdown-item">TV & Appliances</a>
                          <a href="index.jsp?category=<%=C.CATEGORY.SPORTSBOOK%>" class="dropdown-item">Sports & Books</a>
                          <a href="index.jsp?category=<%=C.CATEGORY.OTHERS%>" class="dropdown-item">Other</a>
						    <div class="dropdown-divider"></div>
					  </div>
					</div>
			      <input id="srch-area" class="form-control" type="text" placeholder="Search">
			      <button class="btn btn-primary my-2 my-sm-0 srch-button" type="submit"><i class="fa fa-search"></i></button>
		  		</form>
		  		</li>
        	</ul>
            <ul class="navbar-nav ml-auto">
            	<li class="nav-item">
                    <div class="btn-group">
                          <button type="button" class="btn btn-primary"><%=user_name%></button>
                          <button type="button" class="btn btn-primary dropdown-toggle dropdown-toggle-split filter-button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <span class="sr-only">Toggle Dropdown</span>
                          </button>
                          <div class="dropdown-menu">
                                <a class="dropdown-item" href="index.jsp?editProfile=true">Your Profile</a>
                                <a class="dropdown-item" href="index.jsp?sellItem=true">Sell</a>
                                <a class="dropdown-item" href="index.jsp?history=true">History</a>
                                <form action="logout">
                                    <button class="btn btn-link" type="submit" name="submit">Logout</button>
                                </form>
                                <div class="dropdown-divider"></div>
                          </div>
                    </div>
            	</li>
            </ul>
        </div>
    </div>
</nav>

<%if(editProfile.equals("true")) {%>

<div class="container" style="margin-top: 70px;">
    <h1> </h1>
    <hr>

    <div class="alert alert-info alert-dismissable">
        <a class="panel-close close" data-dismiss="alert">×</a>
        <i class="fa fa-coffee"></i>
        This is an <strong>.alert</strong>. Use this to show important messages to the user.
    </div>
    <h3>Personal info</h3>
    <%--edit profile--%>
    <div class="row">
        <form action="imageupload" method="post" class="form-horizontal col-md-4" role="form" enctype="multipart/form-data">

            <div class="col-md-12 card">
                <div class="text-center card-body">
                    <img src="<%=user_imgurl%>" class="avatar img-circle" style="width:100px;height:100px;border-radius: 50%;" alt="avatar">
                    <h6>Upload a different photo...</h6>

                    <div class="form-group">
                        <label class="col-md-6 control-label"></label>
                        <div class="col-md-12">
                            <input type="file" name="image" class="form-control">
                            <span></span>
                            <input type="submit" class="btn btn-primary" value="Upload">
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <form action="updateprofile" method="POST" class="form-horizontal card col-md-8" role="form">
            <!-- edit form column -->
            <div class="col-lg-12 personal-info">
                <div class="form-group">
                    <label class="col-lg-6 control-label">Name:</label>
                    <div class="col-lg-12">
                        <input class="form-control" name="name" type="text" value="<%=user_name%>" required>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-6 control-label">Email:</label>
                    <div class="col-lg-12">
                        <input class="form-control" type="text" name="email"  value="<%=user_email%>"/>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-lg-6 control-label">Mobile No:</label>
                    <div class="col-lg-12">
                        <input class="form-control" type="text" name="phn" value="<%=user_phnno%>">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-md-6 control-label">New Password:</label>
                    <div class="col-md-12">
                        <input class="form-control" name="password" type="password" value="">
                    </div>
                </div>

                <%--<div class="form-group">--%>
                <%--<label class="col-md-6 control-label">Confirm password:</label>--%>
                <%--<div class="col-md-12">--%>
                <%--<input class="form-control" name="cpassword" type="password" value="">--%>
                <%--</div>--%>
                <%--</div>--%>

                <div class="form-group">
                    <label class="col-md-6 control-label"></label>
                    <div class="col-md-12">
                        <input type="submit" class="btn btn-primary" value="Save Changes">
                        <span></span>
                        <input type="reset" class="btn btn-default" value="Cancel">
                    </div>
                </div>
            </div>
        </form>
        <%--<h1><%=user_longitude%>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=user_latitude%></h1>--%>
    </div>
</div>
<hr>
    <%}else if(sellitem.equals("true")) {%>
<div class="container">
    <h1>Edit Profile</h1><hr>
    <div class="personal-info">
        <form class="col-md-12 form-horizontal" action="sellitem" method="post" role="form" enctype="multipart/form-data">
            <div class="row">
                <div class="col-lg-6 form-group">
                    <label class="control-label">Image : </label>
                    <div class="">
                        <input type="file" class="form-control" name="photo">
                    </div>
                </div>
                <div class="col-lg-6 form-group">
                    <label class="control-label">Title : </label>
                    <div class="">
                        <input class="form-control" type="text" name="title" value="">
                    </div>
                </div>
                <div class="col-lg-6 form-group">
                    <label class="control-label">Category</label>
                    <div class="">
                        <div class="ui-select">
                            <select id="user_time_zone" name="category" class="form-control">
                                <option value="<%=C.CATEGORY.ELECTRONICS%>">Electronics</option>
                                <option value="<%=C.CATEGORY.TVAPPLIANCES%>">TV & Appliances</option>
                                <option value="<%=C.CATEGORY.HOMEFURNITURE%>">Home & Furniture</option>
                                <option value="<%=C.CATEGORY.SPORTSBOOK%>">Sports & Books</option>
                                <option value="<%=C.CATEGORY.OTHERS%>">Others</option>
                            </select>
                        </div>
                    </div>
                </div>
                <div class="col-lg-6 form-group">
                    <label class="control-label">Price : </label>
                    <div class="">
                        <input class="form-control" type="text" name="price" value="">
                    </div>
                </div>
            </div>
            <div class="col-md-12 form-group">
                <label class="control-label">Short Description :</label>
                <div class="">
                    <textarea class="form-control" name="description" rows="5">

                    </textarea>
                </div>
            </div>
            <input type="submit" class="btn btn-dark" value="upload"/>
            <input type="hidden" name="email" value="<%=user_email%>" />
            <input type="hidden" name="longitude" value="<%=user_longitude%>"/>
            <input type="hidden" name="latitude" value="<%=user_latitude%>"/>
        </form>
    </div>
</div>
<hr>
<% }else if(history.equals("true")){
%>
<br><br><br><br>
<div class="container">
    <h1>Item History:</h1>
    <div class="table-responsive">
        <table class="table table-striped table-bordered">
            <thead>
                <tr>
                    <td class="text-dark font-weight-bold">Title</td>
                    <td class="text-dark font-weight-bold">Category</td>
                    <td class="text-dark font-weight-bold">Description</td>
                    <td class="text-dark font-weight-bold">Price</td>
                </tr>
            </thead>
            <tbody>
            <%
                Iterator<Document> iterator = MMongo.find(new DB().getProductsCollection(),new Document(C.FIELD.EMAIL,user_email));
                while (iterator.hasNext()) {
                    Document doc  = iterator.next();
            %>
            <tr>
                <td class="text-muted"><%=doc.getString(C.FIELD.TITLE)%></td>
                <td class="text-muted"><%=doc.getString(C.FIELD.CATEGORY)%></td>
                <td class="text-muted"><%=doc.getString(C.FIELD.DESCRIPTION)%></td>
                <td class="text-muted"><%=doc.getString(C.FIELD.PRICE)%></td>
            </tr>
            <%}%>
            </tbody>
        </table>
    </div>
</div>
<%}else {%>
<header>
    <div id="carouselExampleIndicators" class="carousel slide my-4" style="height:50vh;" data-ride="carousel">
        <ol class="carousel-indicators">
            <li data-target="#carouselExampleIndicators" data-slide-to="0" class="active"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="1"></li>
            <li data-target="#carouselExampleIndicators" data-slide-to="2"></li>
        </ol>
        <div class="carousel-inner" role="listbox">
            <div class="carousel-item active">
                <img class="d-block img-fluid" style="width:100%;margin:0px;height:50vh;" src="images/banner1.jpg" alt="First slide">
            </div>
            <div class="carousel-item">
                <img class="d-block img-fluid" style="width:100%;margin:0px;height:50vh;" src="http://placehold.it/900x350" alt="Second slide">
            </div>
            <div class="carousel-item">
                <img class="d-block img-fluid" style="width:100%;margin:0px;height:50vh;" src="images/banner2.jpg" alt="Third slide">
            </div>
        </div>
        <a class="carousel-control-prev" href="#carouselExampleIndicators" role="button" data-slide="prev">
            <span class="carousel-control-prev-icon" aria-hidden="true"></span>
            <span class="sr-only">Previous</span>
        </a>
        <a class="carousel-control-next" href="#carouselExampleIndicators" role="button" data-slide="next">
            <span class="carousel-control-next-icon" aria-hidden="true"></span>
            <span class="sr-only">Next</span>
        </a>
    </div>
</header>

<div class="container">
<script>
    function changValSlider () {
        document.getElementById('output').innerHTML = document.getElementById('pricelimit').value;
    }
    function gup( name, url ) {
        if (!url) url = location.href;
        name = name.replace(/[\[]/,"\\\[").replace(/[\]]/,"\\\]");
        var regexS = "[\\?&]"+name+"=([^&#]*)";
        var regex = new RegExp( regexS );
        var results = regex.exec( url );
        return results == null ? null : results[1];
    }
    function redirectTo() {
        var pa = gup('category',window.location.href);
        if(pa != null)
            window.location = "index.jsp?category="+ pa+ "&dist="+document.getElementById('pricelimit').value;
        else
            window.location = "index.jsp?dist="+document.getElementById('pricelimit').value;
    }
</script>
    <div class="row">
        <div class="col-lg-3">
            <h1 class="my-4">Shop Name</h1>
            <div class='priceslider'>
                <div class="well">
                    Filter by distance:

                    <input id="pricelimit" type="range" class="span2" value="" oninput="changValSlider()"
                           min="10"
                           max="200"
                           step="10"
                           data-show-value="true"/>
                    <input type="submit" onclick="redirectTo()"/>
                </div>
            </div>
            <p>
                <b>distance</b>: <span id="output">10</span> km
            </p>
        </div>

        <!-- /.col-lg-3 -->

        <div class="col-lg-9">


            <div class="row">
                <%
                    Document fDocument;
                    String category = request.getParameter("category");
                    String dist = request.getParameter("dist");

                    if(category == null || category.equals(""))
                        fDocument=new Document();
                    else
                        fDocument = new Document(C.FIELD.CATEGORY,category);

                    if(dist == null || dist.equals(""))
                        dist="0";

                    Iterator<Document> iterator = MMongo.find(new DB().getProductsCollection(),fDocument);

                    iterator = MMongo.getDistanceIterator(iterator,dist,Double.parseDouble(user_latitude),Double.parseDouble(user_longitude));

                    while(iterator.hasNext()){
                        Document document = iterator.next();
                %>
                <div class="col-lg-4 col-md-6 mb-4">
                    <div class="card h-100">
                        <a href="#"><img class="card-img-top" src="<%=document.getString(C.FIELD.IMGURL)%>" alt="Here I'm"></a>
                        <div class="card-body">
                            <h4 class="card-title">
                                <a href="#"><%=document.getString(C.FIELD.TITLE)%></a>
                            </h4>
                            <h5>&#8377;<%=document.getString(C.FIELD.PRICE)%></h5>
                            <p class="card-text"><%=document.getString(C.FIELD.DESCRIPTION)%></p>
                        </div>
                        <div class="card-footer">
                            <small class="text-muted">&#9733; &#9733; &#9733; &#9733; &#9734;</small>
                        </div>
                    </div>
                </div>
                <%}%>
            </div>
        </div>
    </div>
</div>
<%}
}%>

<!-- Footer -->
<footer class="py-5 bg-black">
    <div class="container">
        <p class="m-0 text-center text-white small">Copyright &copy; Online Deal Finder 2018</p>
    </div>
    <!-- /.container -->
</footer>

    <!-- Bootstrap core JavaScript -->
    <script src="vendor/jquery/jquery.min.js"></script>
    <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>
	
	<script>
	$('#myModal').modal('show');
	</script>
</body>
</html>