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
                <li class="active"><a href="<@spring.url "/employee"/>">Employee</a></li>
                <li><a href="<@spring.url "/department"/>">Department</a></li>
            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <div class="page-header">
        <h1><#if (person.personId)??>Sunting<#else>Tambah</#if> Employee</h1>
    </div>
<#include "../messages.ftl"/>
    <form method="post" action="<#if (person.personId)??><@spring.url "/employee/edit"/>
  <#else><@spring.url "/employee/create"/></#if>" id="personForm" class="form-horizontal">
        <div class="form-group">
        <@spring.bind "person.fullname"/>
            <label for="${spring.status.expression}" class="control-label field-primary col-sm-3 col-md-2">Nama
                Lengkap</label>
            <div class="col-sm-9 col-md-7">
            <@spring.formInput "person.fullname", "class=\"form-control required\" minlength=\"5\""/>
            <#list spring.status.errorMessages as error><label class="error">${error}</label></#list>
            </div>
        </div>
        <div class="form-group">
        <@spring.bind "person.department"/>
            <label for="departments" class="control-label field-primary col-sm-3 col-md-2">Departemen</label>
            <div class="col-sm-9 col-md-5">
                <select name="${spring.status.expression}.deptId" class="chosen-select required"
                        data-placeholder="-- Pilih Departemen --">
                    <option value=""></option>
                <#list departments as dept>
                    <option value="${dept.deptId}" <#if (person.department.deptId)?? && dept.deptId == person.department.deptId>
                            selected="selected"</#if>>${dept.deptName}</option>
                </#list>
                </select>
            <#list spring.status.errorMessages as error><label class="error">${error}</label></#list>
            </div>
        </div>
        <div class="form-group">
        <@spring.bind "person.address"/>
            <label for="${spring.status.expression}"
                   class="control-label field-primary col-sm-3 col-md-2">Alamat</label>
            <div class="col-sm-9 col-md-7">
            <@spring.formTextarea "person.address", "class=\"form-control required\""/>
            <#list spring.status.errorMessages as error><label class="error">${error}</label></#list>
            </div>
        </div>
        <div class="form-group">
        <@spring.bind "person.province"/>
            <label for="${spring.status.expression}"
                   class="control-label field-primary col-sm-3 col-md-2">Propinsi</label>
            <div class="col-sm-9 col-md-7">
            <@spring.formInput "person.province", "class=\"form-control required\""/>
            <#list spring.status.errorMessages as error><label class="error">${error}</label></#list>
            </div>
        </div>
        <div class="form-group">
        <@spring.bind "person.homePhone"/>
            <label for="${spring.status.expression}" class="control-label col-sm-3 col-md-2">Telpon Rumah</label>
            <div class="col-sm-5 col-md-3">
            <@spring.formInput "person.homePhone", "class=\"form-control\""/>
            <#list spring.status.errorMessages as error><label class="error">${error}</label></#list>
            </div>
        </div>
        <div class="form-group">
        <@spring.bind "person.workPhone"/>
            <label for="${spring.status.expression}" class="control-label col-sm-3 col-md-2">Telpon Kantor</label>
            <div class="col-sm-5 col-md-3">
            <@spring.formInput "person.workPhone", "class=\"form-control\""/>
            <#list spring.status.errorMessages as error><label class="error">${error}</label></#list>
            </div>
        </div>
        <div class="form-group">
        <@spring.bind "person.mobilePhone"/>
            <label for="${spring.status.expression}" class="control-label col-sm-3 col-md-2">No. Handphone</label>
            <div class="col-sm-5 col-md-3">
            <@spring.formInput "person.mobilePhone", "class=\"form-control\""/>
            <#list spring.status.errorMessages as error><label class="error">${error}</label></#list>
            </div>
        </div>
        <div class="form-group">
        <@spring.bind "person.email"/>
            <label for="${spring.status.expression}" class="control-label col-sm-3 col-md-2">Email</label>
            <div class="col-sm-5 col-md-3">
            <@spring.formInput "person.email", "class=\"form-control email\""/>
            <#list spring.status.errorMessages as error><label class="error">${error}</label></#list>
            </div>
        </div>
        <div class="form-group">
        <@spring.bind "person.gender"/>
            <label for="${spring.status.expression}" class="control-label field-primary col-sm-3 col-md-2">Jenis
                Kelamin</label>
            <div class="col-sm-7 col-md-6">
                <div class="btn-group" data-toggle="buttons">
                    <label class="btn btn-primary <#if (person.gender)?? && person.gender == "L">active</#if>">
                        <input type="radio" name="${spring.status.expression}" id="option1" autocomplete="off" value="L"
                               <#if (person.gender)?? && person.gender == "L">checked</#if>>Laki-Laki
                    </label>
                    <label class="btn btn-primary <#if (person.gender)?? && person.gender == "P">active</#if>">
                        <input type="radio" name="${spring.status.expression}" id="option2" autocomplete="off" value="P"
                               <#if (person.gender)?? && person.gender == "P">checked</#if>>Perempuan
                    </label>
                </div>
            <#list spring.status.errorMessages as error><label class="error">${error}</label></#list>
            </div>
        </div>
        <div class="form-group">
        <@spring.bind "person.birthPlace"/>
            <label for="${spring.status.expression}" class="control-label col-sm-3 col-md-2">Tempat Lahir</label>
            <div class="col-sm-9 col-md-7">
            <@spring.formInput "person.birthPlace", "class=\"form-control\""/>
            <#list spring.status.errorMessages as error><label class="error">${error}</label></#list>
            </div>
        </div>
        <div class="form-group">
        <@spring.bind "person.birthDate"/>
            <label for="${spring.status.expression}" class="control-label field-primary col-sm-3 col-md-2">Tanggal
                Lahir</label>
            <div class="col-sm-5 col-md-3">
            <@spring.formInput "person.birthDate", "class=\"form-control\" placeholder=\"format: dd-mm-yyyy\""/>
            <#list spring.status.errorMessages as error><label class="error">${error}</label></#list>
            </div>
        </div>
    <@spring.formHiddenInput "person.personId"/>
        <div class="form-group">
            <label class="control-label col-sm-3 col-md-2"></label>
            <div class="col-sm-9 col-md-10">
                <button type="submit" class="btn btn-primary">Simpan</button>
                <a class="btn btn-default" href="<@spring.url "/employee"/>" role="button">Batal</a>
            </div>
        </div>
    </form>
</div>
<#include "../footer.ftl"/>
<script type="text/javascript">
    $(document).ready(function () {
        var frm = $("#personForm");
        $(".container").tooltip({selector: "[data-toggle=tooltip]", placement: "top", container: "body"});
        $(".chosen-select").chosen({allow_single_deselect: true});
        frm.validate({
            ignore: "",
            rules: {email: true}
        });
        frm.find(":input").first().focus();
    });
</script>
</body>
</html>