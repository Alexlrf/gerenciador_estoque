 function redirect(form, destino) {
     form.action = '/controleEstoque/redirect';
     form.enctype = 'application/x-www-form-urlencoded';
     form.redirect.value = destino;
 }