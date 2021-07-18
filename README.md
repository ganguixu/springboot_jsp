<h1>springboot + jsp onChange事件 Test</h1>
<h2>需求:</h2>
<h3>类似与这样的控件</h3>
<div>
用户类型：<input type="text" name="xxx" /><br>
开始日期：<input type="text" name="xxx" /><br>
结束日期：<input type="text" name="xxx" />
<button type="button">导出</button><br>
<button>查询</button>
</div><br>

<h4>导出按钮是否显示的条件如下：</h4>
1、必须选择开始时间和结束时间<br>
2、根据筛选条件查询必须存在数据<br>
3、当点击提交后并且满足如上两个条件，导出按钮变为可选后，如果用户修改了筛选条件则必须把 导出 按钮设为不可选<br>



