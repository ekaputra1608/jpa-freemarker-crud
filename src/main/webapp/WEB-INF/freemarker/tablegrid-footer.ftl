<div class="htgrid-footer">
    <div class="row">
        <div class="col-sm-5 text-muted">Menampilkan&nbsp;
        <#if pages.numberOfElements == 0>0<#else>${startNumber?number + 1}</#if>
            - ${startNumber?number + pages.numberOfElements} dari ${pages.totalElements} items
        </div>
        <div class="col-sm-7">
            <nav class="pull-right">
                <ul class="pagination">
                <#if pages.first>
                    <li class="disabled">
                        <a href="#"><i class="glyphicon glyphicon-step-backward"></i></a>
                    </li>
                    <li class="disabled">
                        <a href="#"><i class="glyphicon glyphicon-chevron-left"></i></a>
                    </li>
                <#else>
                    <li>
                        <a href="#" class="pager-first" title="Halaman awal" data-toggle="tooltip">
                            <i class="glyphicon glyphicon-step-backward"></i></a>
                    </li>
                    <li>
                        <a href="#" class="pager-prev" title="Halaman sebelumnya" data-toggle="tooltip"><i
                                class="glyphicon glyphicon-chevron-left"></i></a>
                    </li>
                </#if>
                    <li class="disabled"><a href="#">Page:</a></li>
                    <li>
                    <#if pages.totalPages == 0>
                        <input type="hidden" name="page" value="1"/>
                        <input type="text" name="pagenumber" value="0" class="form-control" disabled/>
                    <#else>
                        <input type="text" name="page" class="form-control number" value="${dataGrid.page}"/>
                    </#if>
                    </li>
                    <li class="disabled" style="font-weight:600"><a href="#">/&nbsp;&nbsp;${pages.totalPages}</a></li>
                <#if pages.last>
                    <li class="disabled">
                        <a href="#"><i class="glyphicon glyphicon-chevron-right"></i></a>
                    </li>
                    <li class="disabled">
                        <a href="#"><i class="glyphicon glyphicon-step-forward"></i></a>
                    </li>
                <#else >
                    <li>
                        <a href="#" class="pager-next" title="Halaman selanjutnya" data-toggle="tooltip">
                            <i class="glyphicon glyphicon-chevron-right"></i></a>
                    </li>
                    <li>
                        <a href="#" class="pager-last" title="Halaman terakhir" data-toggle="tooltip">
                            <i class="glyphicon glyphicon-step-forward"></i></a>
                    </li>
                </#if>
                </ul>
            </nav>
        </div>
    </div>
    <div class="hidden">
        <input type="hidden" name="sortField" value="${dataGrid.sortField}"/>
        <input type="hidden" name="sortDir" value="${dataGrid.sortDir}"/>
        <input type="hidden" name="pageSize" value="${dataGrid.pageSize}"/>
    </div>
</div>
