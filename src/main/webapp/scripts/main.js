/**
 * Common jquery plugins.
 *
 * @author    Ahmad Fajar
 * @since     29/08/2015, modified: 29/08/2015 21:01
 * @version   1.0.0
 * @category  Javascript
 */
(function ($) {
    /**
     * Toggle checkbox, and set all checkbox to checked
     */
    $.fn.checkAll = function () {
        var checked = false;
        return this.click(function () {
            $(":checkbox").map(function () {
                this.checked = !checked;
                return true;
            });
            checked = this.checked;
        });
    };

    /**
     * Initialize htmlgrid action script with jquery.
     *
     * @param {int} totalPages
     */
    $.fn.HTGridAction = function (totalPages) {
        var me = $(this).is("form") ? $(this) : $(this).closest("form");
        me.find(".htgrid-cell-header > .cell-header-inner").click(function () {
            if ($(this).attr("rel")) {
                var value = $(this).attr('rel'), sortOrder = $("input[name=sortDir]", me).val();
                $("input[name=sortField]", me).val(value);
                if (sortOrder == "asc") {
                    $("input[name=sortDir]", me).val("desc");
                }
                else {
                    $("input[name=sortDir]", me).val("asc");
                }
                me.submit();
            }
        });
        var pagination = $(".htgrid-footer", me);
        $("li.disabled > a", pagination).click(function (e) {
            e.preventDefault();
        });
        $('.pager-first', pagination).click(function () {
            $("input[name=page]", pagination).val(1);
            me.submit();
        });
        $(".pager-prev", pagination).click(function () {
            var page = parseInt($("input[name=page]", me).val());
            $("input[name=page]", pagination).val(page - 1);
            me.submit();
        });
        $(".pager-next", pagination).click(function () {
            var page = parseInt($("input[name=page]", me).val());
            $("input[name=page]", pagination).val(page + 1);
            me.submit();
        });
        $(".pager-last", pagination).click(function () {
            $("input[name=page]", pagination).val(totalPages);
            me.submit();
        });
        $("input[name=page]", pagination).keypress(function (e) {
            if (e.which == 10 || e.which == 13) {
                me.submit();
            }
        });
    };

}(jQuery));