<html>
<head>
    <title>Hubtangle - your entry point</title>
    <meta name="layout" content="main"/>
    <r:require modules="application, social"/>
    <r:layoutResources/>
</head>

<body class="home">
<header>
    <div class="wrapper cf">
        <layout:include template="logo" />
</header>

<!-- MAIN -->
<div id="main">
    <div class="wrapper cf">

        <layout:include template="login"/>

    </div>
    <!-- ENDS WRAPPER -->
</div>
<!-- ENDS MAIN -->
</body>
</html>