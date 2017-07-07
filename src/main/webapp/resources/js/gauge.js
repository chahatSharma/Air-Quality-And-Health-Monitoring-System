$(function () {

                var chart = new Highcharts.Chart({
                    chart: {
                        type: 'gauge',
                        plotBackgroundColor: null,
                        plotBackgroundImage: null,
                        plotBorderWidth: 0,
                        plotShadow: false,
                        renderTo : 'chartcontainer'
                    },
                    title: {
                        text: 'Indoor AQI '
                    },
                    pane: {
                        startAngle: -150,
                        endAngle: 150,
                        background: [{
                                backgroundColor: {
                                    linearGradient: {x1: 0, y1: 0, x2: 0, y2: 1},
                                    stops: [
                                        [0, '#FFF'],
                                        [1, '#333']
                                    ]
                                },
                                borderWidth: 0,
                                outerRadius: '109%'
                            }, {
                                backgroundColor: {
                                    linearGradient: {x1: 0, y1: 0, x2: 0, y2: 1},
                                    stops: [
                                        [0, '#333'],
                                        [1, '#FFF']
                                    ]
                                },
                                borderWidth: 1,
                                outerRadius: '107%'
                            }, {
                                // default background
                            }, {
                                backgroundColor: '#DDD',
                                borderWidth: 0,
                                outerRadius: '105%',
                                innerRadius: '103%'
                            }]
                    },
                    // the value axis
                    yAxis: {
                        min: 0,
                        max: 500,
                        minorTickInterval: 'auto',
                        minorTickWidth: 1,
                        minorTickLength: 10,
                        minorTickPosition: 'inside',
                        minorTickColor: '#666',
                        tickPixelInterval: 30,
                        tickWidth: 2,
                        tickPosition: 'inside',
                        tickLength: 10,
                        tickColor: '#666',
                        labels: {
                            step: 2,
                            rotation: 'auto'
                        },
                        title: {
                            text: 'km/h'
                        },
                        plotBands: [{
                                from: 0,
                                to: 50,
                                color: '#55BF3B' // green
                            }, {
                                from: 50,
                                to: 100,
                                color: '#DDDF0D' // yellow
                            }, {
                                from: 100,
                                to: 150,
                                color: '#ffa500' // orange
                            }, {
                                from: 150,
                                to: 200,
                                color: '#DF5353' // red
                            }, {
                                from: 200,
                                to: 300,
                                color: '#551a8b' // purple
                            }
                            , {
                                from: 300,
                                to: 500,
                                color: '#b03060' // maroon
                            }
                        ]
                    },
                    series: [{
                            name: 'AQI',
                            data: [80],
                            tooltip: {
                                valueSuffix: ''
                            }
                        }]

                },
                // Add some life
                function (chart) {
//                    if (!chart.renderer.forExport) {
//                        setInterval(function () {
//                            var point = chart.series[0].points[0],
//                                    newVal,inc=0;
////                                    inc = Math.round((Math.random() - 0.5) * 20);
//
//                            newVal = point.y + inc;
//                            if (newVal < 0 || newVal > 200) {
//                                newVal = point.y - inc;
//                            }
//
//                            point.update(68);
//
//                        }, 3000);
//                    }
                });
            });
