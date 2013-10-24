<%--
  Created by IntelliJ IDEA.
  User: mkubryn
  Date: 24.10.13
  Time: 21:30
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>Filestore files:</title>
</head>


<body>


<table border="1">
  <tr style="background-color: mediumaquamarine; font-style: italic">
      <td>dsId</td>
      <td>File name</td>
      <td>Location</td>
      <td>Size</td>
      <td>ContentType</td>
      <td>Download</td>
      <td>Delete</td>
  </tr>

   <g:each in="${dsFiles}" var="file" >
       <tr>
            <td>${file.id}</td>
            <td>${file.name}</td>
            <td>${file.location}</td>
            <td>${file.size}</td>
            <td>${file.contentType}</td>
            <td><a href="/dataserver/r/download/${file.id}">go</a></td>
            <td><a href="/dataserver/r/delete/${file.id}">go</a></td>
       </tr>
   </g:each>

</table>


</body>
</html>