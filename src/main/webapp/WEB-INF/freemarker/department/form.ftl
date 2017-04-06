<#import "/spring.ftl" as spring />
<#assign xhtmlCompliant = true in spring>
<!doctype html>
<html>
<head>
<#include "../head-meta.ftl"/>
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
                <li><a href="<@spring.url "/"/>">Home</a></li>
                <li><a href="<@spring.url "/employee"/>">Employee</a></li>
                <li class="active"><a href="<@spring.url "/department"/>">Department</a></li>
            </ul>
        </div>
    </div>
</nav>

<section class="container">
    <div class="page-header">
        <h1><#if (department.deptId)??>Sunting<#else>Tambah</#if> Departemen</h1>
    </div>
<#include "../messages.ftl"/>
    <form method="post" action="<#if (department.deptId)??><@spring.url "/department/edit"/>
  <#else><@spring.url "/department/create"/></#if>" id="deptForm" class="form-horizontal">
        <div class="form-group">
        <@spring.bind "department.deptName"/>
            <label for="${spring.status.expression}"
                   class="control-label field-primary col-sm-3 col-md-2">Departemen</label>
            <div class="col-sm-9 col-md-7">
            <@spring.formInput "department.deptName", "class=\"form-control required\" minlength=\"3\""/>
            <#list spring.status.errorMessages as error><label class="error">${error}</label></#list>
            </div>
        </div>
        <div class="form-group">
        <@spring.bind "department.description"/>
            <label for="${spring.status.expression}" class="control-label col-sm-3 col-md-2">Keterangan</label>
            <div class="col-sm-9 col-md-10">
            <@spring.formInput "department.description", "class=\"form-control\""/>
            <#list spring.status.errorMessages as error><label class="error">${error}</label></#list>
            </div>
        </div>
    <@spring.formHiddenInput "department.deptId"/>
        <div class="form-group">
            <label class="control-label col-sm-3 col-md-2"></label>
            <div class="col-sm-9 col-md-10">
                <button type="submit" class="btn btn-primary">Simpan</button>
                <a class="btn btn-default" href="<@spring.url "/department"/>" role="button">Batal</a>
            </div>
        </div>
    </form>
</section>
<#include "../footer.ftl"/>
<script type="text/javascript">
    $(document).ready(function () {
        var frm = $("#deptForm");
        $(".container").tooltip({selector: "[data-toggle=tooltip]", placement: "top", container: "body"});
        frm.validate({
            ignore: ""
        });
        frm.find(":input").first().focus();
    });
</script>
</body>
</html>