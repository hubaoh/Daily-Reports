/**
 * Created by 赵传志 on 2018/4/10.
 */

'use strict';

app.controller('reportSummaryController', ['$scope', function ($scope) {

    var complainCharts;
    var adStatChart;
    var timeInterval = 2000;

   

    // 投诉报表
    $scope.makeComplainChart = function () {
        var ech = document.getElementById('personalTotalScoreChart');
        complainCharts = echarts.init(ech);
        var option = {
            tooltip: {
                trigger: 'axis',
                axisPointer: { // 坐标轴指示器，坐标轴触发有效
                    type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                }
            },
            legend: {
                data: ['数量'],
                x: '50%',
                textStyle: {
                    color: '#DCDCDC'
                },
                itemWidth: 20,
                itemHeight: 20,
                borderRadius: 20
            },
            grid: {
                left: '3%',
                right: '4%',
                bottom: '3%',
                top: '20%',
                borderColor: '#51585F',
                containLabel: true
            },
            xAxis: [{
                type: 'category',
                data: ['已提交', '补写','未提交'],
                axisLine: {
                    lineStyle: {
                        color: '#51585F'
                    }
                },
                axisLabel: {
                    textStyle: {
                        color: "#fff"
                    }
                }
            }],
            yAxis: [{
                type: 'value',
                axisLine: {
                    lineStyle: {
                        color: '#51585F'
                    }
                },
                axisLabel: {
                    textStyle: {
                        color: "#fff"
                    }
                }

            }],
            series: [{
                name: '数量',
                type: 'bar',
                itemStyle: {
                    normal: {
                        color: '#DB0D4A'
                    }
                },

                barWidth: '40%',
                data:[50,6,2]
            }
            ],
            animationEasing: 'circularOut',
            animationDuration: 2000,
            animationDelay: 700
        };

        complainCharts.setOption(option);
    };

    $scope.makeComplainChart();

    // 改变时间的同时改变报表的数据
    $scope.$watch('complaintTime', function (newVal, oldVal) {
        if (newVal !== oldVal) {
            if (newVal > new Date()) {
                alert("error:该日期还没有数据");
                currentDate();
            }
            complainCharts.dispose();
            complainChartData();
        }
    }, true);

    // 个人日报考评分数统计报表
    $scope.makeAdStatChart = function () {
        var ech = document.getElementById('personalReportChart');
        adStatChart = echarts.init(ech);
        var option = {
            tooltip: {
                trigger: 'item',
                formatter: "{a} : {b} <br/>数量 : {c}<br/> 占比  : {d}%"
            },
            legend: {
                orient: 'horizontal',
                x: 'center',
                y: '0',
                data: ['优秀', '良好', '及格', '不及格'],
                textStyle: {
                    color: "#fff"
                }
            },
            color: ["#DB0D4A", "#328DCB", "#00D0AA", "#EBE400"],
            series: [{
                name: '等级',
                type: 'pie',
                radius: ['25%', '75%'],
                center: ['50%', '60%'],
                data: [{value:80,name:"优秀"},{value:70,name:"良好"},{value:60,name:"及格"},{value:50,name:"不及格"}],
                label: {
                    normal: {
                        formatter: "数量 : {c}\n占比 : {d}%",
                        position: "inner",
                        show: true
                    }
                },
                itemStyle: {
                    emphasis: {
                        shadowBlur: 10,
                        shadowOffsetX: 0,
                        shadowColor: 'rgba(0, 0, 0, 0.5)'
                    }
                }
            }]
        };
        adStatChart.setOption(option);
    };
    $scope.makeAdStatChart();

    // 投诉报表日历工具事件
    $scope.complainTimeTool = function ($event) {
        $event.preventDefault();
        $event.stopPropagation();
        $scope.complaintTimeOpened = true;
    };


}]);

