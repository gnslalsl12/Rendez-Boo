import React, { Component, useEffect, useState } from 'react'
import ApexCharts from 'react-apexcharts'
import { BiHappyHeartEyes } from 'react-icons/bi'

const RocketRadar = (props) => {
  const emotion = props.rocketUserEmotion

  const [series, setSeries] = useState([
    {
      name: 'Series 1',
      data: [0, 0, 0, 0, 0, 0],
    },
  ])

  const [options, setoptions] = useState({
    chart: {
      height: 350,
      type: 'radar',
    },
    dataLabels: {
      enabled: true,
    },
    plotOptions: {
      radar: {
        size: 140,
        polygons: {
          strokeColors: '#e9e9e9',
          fill: {
            colors: ['#f8f8f8', '#fff'],
          },
        },
      },
    },
    title: {
      text: '다이어그램',
    },
    colors: ['#FF4560'],
    markers: {
      size: 4,
      colors: ['#fff'],
      strokeColor: '#FF4560',
      strokeWidth: 2,
    },
    tooltip: {
      y: {
        formatter: function (val) {
          return val
        },
      },
    },
    xaxis: {
      categories: ['행복', '놀람', '공포', '당황', '분노', '슬픔'],
    },
    yaxis: {
      tickAmount: 7,
      labels: {
        formatter: function (val, i) {
          if (i % 2 === 0) {
            return val
          } else {
            return ''
          }
        },
      },
    },
  })

  useEffect(() => {
    setSeries([
      {
        name: 'Series',
        data: [
          emotion.happiness,
          emotion.surprise,
          emotion.fear,
          emotion.disgust,
          emotion.anger,
          emotion.sadness,
        ],
      },
    ])
  }, [props])

  return (
    <div id="chart">
      <ApexCharts options={options} series={series} type="radar" height={350} />
    </div>
  )
}

export default RocketRadar
