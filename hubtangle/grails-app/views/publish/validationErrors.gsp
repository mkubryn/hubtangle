<g:hasErrors bean="${bean}">
  <ul>
   <g:eachError var="err" bean="${bean}">
       <li>${err}</li>
   </g:eachError>
  </ul>
</g:hasErrors>