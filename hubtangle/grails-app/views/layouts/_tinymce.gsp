<script type="text/javascript" src="${request.contextPath}/js/tinymce/tinymce.min.js"></script>
<script type="text/javascript">
tinymce.init({
    selector: "textarea",
    plugins: [
        "advlist autolink lists link image charmap print preview anchor",
        "searchreplace visualblocks code fullscreen",
        "insertdatetime media table contextmenu paste save"
    ],
    
    toolbar: "save | insertfile undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",

    save_enablewhendirty: true
});

</script>

<script>
function submitForm() {
	tinymce.triggerSave();
	document.forms[0].submit();
}
</script>
