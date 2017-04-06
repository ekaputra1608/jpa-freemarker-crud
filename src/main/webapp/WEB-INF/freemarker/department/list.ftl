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

<#assign caret>
<span class="pull-right glyphicon glyphicon-triangle-<#if dataGrid.sortDir == "asc">top<#else>bottom</#if>"></span>
</#assign>
<section class="container">
    <div class="page-header">
        <h1>Daftar Departemen</h1>
    </div>
<#include "../messages.ftl"/>
    <form method="post" action="<@spring.url "/department"/>" id="tableForm" class="form-horizontal">
        <article class="table-responsive">
            <table class="table htgrid">
                <thead>
                <tr>
                    <th class="htgrid-cell-header text-right">#</th>
                    <th class="htgrid-cell-header text-center"><label><input type="checkbox" id="toggle-check"/></label>
                    </th>
                    <th class="htgrid-cell-header">
                        <div class="cell-header-inner" title="Klik untuk mengurutkan" data-toggle="tooltip"
                             rel="deptName">
                        <#if dataGrid.sortField == "deptName">${caret}</#if>Departemen
                        </div>
                    </th>
                    <th class="htgrid-cell-header hidden-xs">Keterangan</th>
                    <th class="htgrid-cell-header">
                        <div class="cell-header-inner" title="Klik untuk mengurutkan" data-toggle="tooltip"
                             rel="COUNT(p.personId)"><#if dataGrid.sortField == "COUNT(p.personId)">${caret}</#if>#
                            Employee
                        </div>
                    </th>
                    <th class="htgrid-cell-header text-center hidden-xs">Action</th>
                </tr>
                </thead>
                <tbody>
                <#escape x as x?html>
                    <#assign startNumber="${(dataGrid.page - 1) * dataGrid.pageSize}"/>
                    <#list dataGrid.entries as item>
                    <tr>
                        <#assign offset="${(startNumber?number + item_index + 1)}">
                        <td class="text-right">${offset}</td>
                        <td class="text-center"><label>
                            <input type="checkbox" id="cb${offset}" name="dept" value="${item.deptId}"/></label></td>
                        <td>${item.deptName}</td>
                        <td class="hidden-xs">${item.description}</td>
                        <td>${item.numberOfPerson!}</td>
                        <td class="text-center hidden-xs">
                            <a class="btn btn-xs btn-default" href="<@spring.url "/department/edit/${item.deptId}"/>"
                               title="Sunting" data-toggle="tooltip" role="button">
                                <span class="glyphicon glyphicon-edit"></span></a>
                        </td>
                    </tr>
                    </#list>
                </#escape>
                </tbody>
            </table>
        </article>
    <#include "../tablegrid-footer.ftl"/>
    </form>
    <div class="form-group">
        <div class="pull-right">
            <a class="btn btn-default" href="<@spring.url "/department/create"/>" title="Tambah departemen baru"
               data-toggle="tooltip" role="button"><span class="glyphicon glyphicon-plus"></span> New Department</a>
            <button type="button" id="delete-department" class="btn btn-danger" title="Hapus departemen"
                    data-toggle="tooltip"><span class="glyphicon glyphicon-trash"></span> Delete Department
            </button>
        </div>
    </div>
</section>
<#include "../footer.ftl"/>
<script type="text/javascript">
    $(document).ready(function () {
        $(".container").tooltip({selector: "[data-toggle=tooltip]", placement: "top", container: "body"});
        $("#toggle-check").checkAll();
        $("#tableForm").HTGridAction(${dataGrid.totalPages});
        $("#delete-department").click(function () {
            var nchk = 0;
            $(":input[id^=cb]").each(function () {
                if (this.checked == true) {
                    nchk++;
                }
            });
            if (nchk > 0) {
                $("#tableForm").attr("action", "<@spring.url "/department/delete"/>").submit();
            }
        });
    });
</script>
</body>
</html>