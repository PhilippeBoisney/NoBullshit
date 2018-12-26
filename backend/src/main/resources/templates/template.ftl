<!-- MAIN MACRO -->
<#macro mainLayout title="" description="">
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="icon" href="/favicon.ico">

        <title>${title} | ${description}</title>

        <!-- Bootstrap core CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.1/css/all.css" integrity="sha384-gfdkjb5BdAXd+lj+gudLWI+BXq4IuLW5IT+brZEZsLFm++aCMlF1V92rMkPaX4PP" crossorigin="anonymous">

        <!-- Custom styles -->
        <link rel="stylesheet" type="text/css" href="/styles/main.css">
    </head>

    <!-- Body -->
    <body class="text-center">
        <div class="body-container d-flex h-100 p-3 mx-auto flex-column">
            <#nested />
        </div>
    </body>

</html>
</#macro>

<!-- HEADER MACRO -->
<#macro headerLayout currentUrl="">
<header class="masthead mb-auto">
    <div class="inner">
        <h3 class="masthead-brand"><a href="/">NoBullshit</a></h3>
        <nav class="nav nav-masthead justify-content-center">
            <#if currentUrl = "/submit">
                <a class="nav-link active" href="/submit">Submit a Job</a>
            <#else>
                <a class="nav-link" href="/submit">Submit a Job</a>
            </#if>
            <a class="nav-link" href="https://github.com/PhilippeBoisney/NoBullshit"><i class="fab fa-github"></i></a>
        </nav>
    </div>
</header>
</#macro>

<!-- CONTENT MACRO -->
<#macro contentLayout>
<main role="main" class="inner content">
    <#nested />
</main>
</#macro>

<!-- FOOTER MACRO -->
<#macro footerLayout>
<footer class="mastfoot mt-auto">
    <div class="inner">
        Made with ❤️in Lyon, France.
    </div>
</footer>
</#macro>