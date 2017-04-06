<#import "/spring.ftl" as spring />

<!doctype html>
<html>
<head>
<#include "head-meta.ftl"/>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                    aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="<@spring.url "/"/>">JPA-Crud Project</a>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav">
                <li class="active"><a href="<@spring.url "/"/>">Home</a></li>
                <li><a href="<@spring.url "/employee"/>">Employee</a></li>
                <li><a href="<@spring.url "/department"/>">Department</a></li>
            </ul>
        </div>
    </div>
</nav>

<section class="jumbotron">
    <article class="container">
        <h1>Hello, world!</h1>
        <p>Selamat datang, pada halaman utama belajar membuat web aplikasi menggunakan Springframework-MVC,
            dan Freemarker. Project ini berisi contoh-contoh dasar implementasi CRUD pada MySQL database dengan
            menggunakan JPA dan Hibernate.</p>
        <p>Tampilan pada halaman ini diambil dari template "Bootstrap".</p>
        <p><a class="btn btn-primary btn-lg" href="http://getbootstrap.com/getting-started/" role="button">Learn
            more &raquo;</a></p>
    </article>
</section>

<section class="container">
    <article class="row">
        <div class="col-md-4">
            <h2>Employee</h2>
            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo,
                tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem
                malesuada magna mollis euismod. Donec sed odio dui. </p>
            <p><a class="btn btn-warning" href="<@spring.url "/employee"/>" role="button">View details &raquo;</a></p>
        </div>
        <div class="col-md-4">
            <h2>New Employee</h2>
            <p>Donec id elit non mi porta gravida at eget metus. Fusce dapibus, tellus ac cursus commodo,
                tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem
                malesuada magna mollis euismod. Donec sed odio dui. </p>
            <p><a class="btn btn-warning" href="<@spring.url "/employee/create"/>" role="button">View
                details &raquo;</a></p>
        </div>
        <div class="col-md-4">
            <h2>Department</h2>
            <p>Donec sed odio dui. Cras justo odio, dapibus ac facilisis in, egestas eget quam.
                Vestibulum id ligula porta felis euismod semper. Fusce dapibus, tellus ac cursus commodo,
                tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus.</p>
            <p><a class="btn btn-warning" href="<@spring.url "/department"/>" role="button">View details &raquo;</a></p>
        </div>
    </article>
</section>
<#include "footer.ftl"/>
</body>
</html>