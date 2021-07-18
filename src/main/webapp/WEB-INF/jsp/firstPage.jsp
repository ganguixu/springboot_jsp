<%@ page contentType="text/html;charset=UTF-8" language="java" import="java.util.*" pageEncoding="utf-8" %>
<%@ page isELIgnored="false" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录</title>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <script>
        $(function () {
            $('#beginDate').datepicker({dateFormat:'yy-mm-dd'});
            $('#endDate').datepicker({dateFormat:'yy-mm-dd'});

            $('#submitBut').click(function () {
                const type = $('#type').val();
                const beginDate = $('#beginDate').val();
                const endDate = $('#endDate').val();
                alert('用户类型：'+type+'；开始时间：'+beginDate+"；结束时间："+endDate);
                if (beginDate != "" && endDate == "") {
                    alert("如果选择了开始时间则必须选择结束时间");
                    return;
                }
                if (beginDate == "" && endDate != "") {
                    alert("不能仅选择结束时间");
                    return;
                }
                if (beginDate != "" && endDate != "") {
                    if (beginDate > endDate){
                        alert("开始时间不得大于结束时间");
                        return;
                    }
                }

                $.ajax({
                    url:'findUserAll',
                    type:'post',
                    dateType:'json',
                    data:{
                        type:type,
                        beginDate:beginDate,
                        endDate:endDate
                    },
                    success: function (result) {
                        alert(result.length);
                        if(result.length>0 && endDate != "" && beginDate != ""){
                            // $('#downloadBtn').attr('disabled',false);
                            $('#download').val("1").change();
                        }else {
                            $('#download').val("0").change();
                        }

                    },
                    error: function (e) {
                        alert(e);
                    }
                })
            });
        });

        function showBtn() {
            const value = $('#download').val();
            console.log(value);
            if(value == 1){
                $('#downloadBtn').attr('disabled',false);
            }else {
                $('#downloadBtn').attr('disabled',true);
            }
        }

        function changeVal() {
            $('#downloadBtn').attr('disabled',true);
        }
    </script>
</head>
<body>
    <div>
        <input type="hidden" name="type" value="${type}" />
        <input type="hidden" name="beginDate" value="${beginDate}" />
        <input type="hidden" name="endDate" value="${endDate}" />
        <input id="download" onchange="showBtn()" type="hidden" name="download" value="0"/>
    </div>
    <label>
        用户类型：
        <select id="type" onchange="changeVal()" name="type">
            <option value="0">请选择</option>
            <option value="1">普通用户</option>
            <option value="2">尊享用户</option>
            <option value="3">VIP</option>
            <option value="4">VVIP</option>
        </select>
    </label>

    <label>
        <p>开始时间：<input onchange="changeVal()" type="text" id="beginDate"></p>
    </label>
    <label>
        <p>
            结束时间：<input onchange="changeVal()" type="text" id="endDate">
            <button id="downloadBtn" disabled="disabled">导出</button>
        </p>
    </label>
    <button id="submitBut">提交</button>
</body>
</html>
