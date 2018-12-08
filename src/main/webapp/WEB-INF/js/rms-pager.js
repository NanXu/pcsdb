$(function() {
	// 分页页数
	$('#pageNumberTools').bind("pageChange", function(e, index) {
	    var pageNo = index + 1;
	    $('#pageNo').val(pageNo);
	    $('#searchForm').submit();
	});
	// 分页条数
	$('#pageNumberTools').bind("sizeChange", function(e, num) {
	    var pageSize = num;
	    $('#pageSize').val(pageSize);
	    $('#searchForm').submit();
	});
	$(".icon_reload").click(function() {
		$("#searchForm")[0].reset();
		$("#searchForm select").resetValue();
	});
    
	// 全选checkbox
    $('.checkAllCB').click(function() {
        $('.checkRowCB').attr('checked', this.checked);
        $('.checkRowCB').each(function() {
            changeRowColor(this);
        })
    });
    // 表格中的checkbox
    $(".checkRowCB").change(function() {
        changeRowColor(this);
        if (!this.checked) {
            $('.checkAllCB').attr('checked', this.checked);
        }
    });
    // 改变表格行的颜色
    function changeRowColor(row) {
        if (row.checked) {
            $(row).parent().parent().addClass('trSelect');
        } else {
            $(row).parent().parent().removeClass('trSelect');
        }
    }

});

