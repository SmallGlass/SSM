<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<div class="data_list">
	<div class="dataHeader navi" id="newListNav">

	</div>
	<div class="datas news_type_list">
		<ul id="newListUl">

		</ul>
	</div>
	<div style="text-align:center;">
		<ul id="useroption"></ul>
	</div>
</div>
<script type="text/javascript">
    $(function () {
        $.ajax(
            {
                url:'newsList',
                type:'POST',
                data:{'typeId':${typeId}},
                dataType:'JSON',
                success:function (callback) {
                    var newsList=callback.list;
                    var page=callback.page;
                    var typeName=newsList[0].typeName;
                    var nav="当前位置：&nbsp;&nbsp;<a href='goIndex'>主页</a>&nbsp;&nbsp;>&nbsp;&nbsp;<a href='news?action=list&typeId=${typeId}'>"+typeName+"</a>";
					$("#newListNav").append(nav);/*注意是（），{}是jsp的填充方式*/
					var content="";
					for(var i=0;i<newsList.length;i++){
                        var publishDate = newsList[i].publishDate;
                        content+="<li><span>"+publishDate.substring(0,10)+"</span>&nbsp;&nbsp;<a href='news?action=show&newsId="+newsList[i].newsId+"' title='"+newsList[i].title+ "'>"+newsList[i].title.substring(0,42)+"</a></li>";
                    };
                    $("#newListUl").html(content);
                    var currentPage = page.curentNumber; //当前页数
                    var pageCount = page.lastNumber; //总页数
					var numberOfPages=page.size;//页面大小
                    var options = {
                        bootstrapMajorVersion: 3, //版本

                        currentPage: currentPage, //当前页数

                        totalPages: pageCount, //总页数

                        numberOfPages: numberOfPages,
                        shouldShowPage:true,//是否显示该按钮
                        itemTexts: function (type, page, current) {
                            switch (type) {
                                case "first":
                                    return "首页";
                                case "prev":
                                    return "上一页";
                                case "next":
                                    return "下一页";
                                case "last":
                                    return "末页";
                                case "page":
                                    return page;
                            }
                        },
                        onPageClicked: function (event, originalEvent, type, page) {
                            $.ajax({
                                async: true,
                                url: "newsList",
                                type: "post",
                                dataType : "json",
                                data: {'typeId':${typeId},'pageIndex':page},
                                cache: false,
                                success: function (callback) {
                                    var newsList=callback.list;
                                    var page=callback.page;
                                    var content="";
                                    for(var i=0;i<newsList.length;i++){
                                        content+="<li><span></span>&nbsp;&nbsp;<a href='news?action=show&newsId="+newsList[i].newsId+"' title='"+newsList[i].title+ "'>"+newsList[i].title.substring(0,42)+"</a></li>";
                                    };
                                    $("#newListUl").html(content);
                                }
							});
						}
					};
                    $('#useroption').bootstrapPaginator(options);
                }

            });
    });
</script>