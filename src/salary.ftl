<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title></title>
    <style type="text/css">
        table{border-spacing:1px; border:1px solid #A2C0DA;}
        td, th{border-collapse:collapse;padding: 0;width:60px}
        thead tr th{background:rgba(152, 157, 162, 0.32);border:1px solid grey;}

        tbody tr td{border:1px solid black; vertical-align:middle;}

        caption,tfoot{display:none;}
    </style>
</head>
<body>
<div >
	<span>您好，${username}，您的${date?string('yyyy年MM月')}工资明细如下：
</div>
<table  cellspacing="1" >

    <thead>
    <tr>
        <th>姓名</th>
        <th>岗级</th>
        <th>岗级工资</th>
        <th>绩效工资</th>
        <th>绩效得分</th>
        <th>应发绩效</th>
        <th>津贴合计</th>
        <th>学历津贴</th>
        <th>司龄津贴</th>
        <th>通车补助</th>
        <th>餐费</th>
        <th>出勤、病假扣除</th>
        <th>加班费/奖金</th>
        <th>小计</th>
        <th>小计</th>
        <th>社保缴纳基数</th>
        <th>养老保险费</th>
        <th>失业保险费</th>
        <th>医疗保险费</th>
        <th>大额保险</th>
        <th>公积金缴纳基数</th>
        <th>扣除数</th>
        <th>冬季采暖</th>
        <th>公积金贷款利息</th>
        <th>应纳税所得</th>
        <th>代扣个税</th>
        <th>独生子女费</th>
        <th>合计实发金额</th>

    </tr>
    </thead>
    <tbody>
    <tr>
        <td>${username}</td>
    <#list salary as cell>
        <td>${cell}</td>
    </#list>

    </tr>
    </tbody>

</table>
</body>
</html>