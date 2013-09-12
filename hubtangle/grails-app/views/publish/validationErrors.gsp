<g:hasErrors bean="${entry}">
  <ul>
   <g:eachError var="err" bean="${entry}">
       <li>${err}</li>
   </g:eachError>
  </ul>
</g:hasErrors>