<#if messages??>
<aside class="alert <#if messages.type == "error">alert-danger<#else>alert-success</#if> alert-dismissable fade in">
    <button type="button" class="close" data-dismiss="alert" aria-hidden="true">&times;</button>
    <div class="alert-md <#if messages.type == "error">mb-warning-medium<#else>mb-information-medium</#if>">
    ${messages.message?html}
    </div>
</aside>
</#if>