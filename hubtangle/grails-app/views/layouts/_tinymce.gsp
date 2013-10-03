<script type="text/javascript" src="${request.contextPath}/js/tinymce/tinymce.min.js"></script>
<script type="text/javascript">
tinymce.init({
    selector: "textarea",
    plugins: [
        "link image"
    ],
    
    toolbar: "undo redo | styleselect | bold italic | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | link image",

    save_enablewhendirty: true,
    menubar:false,
    statusbar: false,
});

</script>

<script>
function submitForm() {
	tinymce.triggerSave();
	document.forms[0].submit();
}
</script>
