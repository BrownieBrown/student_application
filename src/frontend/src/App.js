import {deleteStudent, getAllStudents} from './client'
import { useState, useEffect } from 'react'
import {Layout, Menu, Breadcrumb, Empty, Table, Spin, Button, Badge, Tag, Avatar, Popconfirm} from 'antd'
import {
  DesktopOutlined,
  PieChartOutlined,
  FileOutlined,
  TeamOutlined,
  UserOutlined, PlusOutlined,
} from '@ant-design/icons'

import StudentDrawerForm from "./StudentDrawerForm";

import './App.css'
import {errorNotification, successNotification} from "./Notification";

const { Header, Content, Footer, Sider } = Layout
const { SubMenu } = Menu

const TheAvatar = ({name}) => {
  let trim = name.trim();
  if (trim.length === 0) {
    return <Avatar icon={<UserOutlined/>}/>
  }
  const split = trim.split(" ");
  if (split.length === 1) {
    return <Avatar>{name.charAt(0)}</Avatar>
  }
  return <Avatar>
    {`${name.charAt(0)}${name.charAt(name.length - 1)}`}
  </Avatar>
}

const removeStudent = (studentId, callback) => {
  deleteStudent(studentId).then(() => {
    successNotification("Student deleted", `Student ${studentId} successfully deleted`)
    callback()
  })
}

const columns = fetchStudents => [
  {
   title: '',
   dataIndex: 'avatar',
   key: 'avatar',
   render: (text, student) => <TheAvatar name={student.name} />
  },
  {
    title: 'Id',
    dataIndex: 'id',
    key: 'id',
  },
  {
    title: 'Name',
    dataIndex: 'name',
    key: 'name',
  },
  {
    title: 'Email',
    dataIndex: 'email',
    key: 'email',
  },
  {
    title: 'Gender',
    dataIndex: 'gender',
    key: 'gender',
  },
  {
    title: 'Actions',
    key: 'actions',
    render: (text, student) =>
        <div>
          <Popconfirm
              placement='topRight'
              title={`Are you sure to delete ${student.name}`}
              onConfirm={() => removeStudent(student.id, fetchStudents)}
              okText='Yes'
              cancelText='No'>
            <Button value="small">Delete</Button>
          </Popconfirm>
          <Button value="small">Edit</Button>
        </div>
  }
]

function App() {
  const [students, setStudents] = useState([])
  const [isCollapsed, setIsCollapsed] = useState(false)
  const [isFetching, setIsFetching] = useState(true)
  const [isShowDrawer, setIsShowDrawer] = useState(false)

  const fetchStudents = () => {
    getAllStudents()
      .then((response) => response.json())
      .then((data) => {
        console.log(data)
        setStudents(data)
      }).catch(err => {
          console.log(err.response)
          err.response.json().then(res => {
          console.log(res)
          errorNotification("There was an issue", `${res.message} [statusCode:${res.status}]`)
      })
    }).finally(() => setIsFetching(false))
  }
  useEffect(() => {
    fetchStudents()
  }, [])

  const renderStudents = () => {
    if (isFetching) {
      return <Spin />
    }
    if (students.length <= 0) {
      return <Empty />
    }
    return (
        <>
          <StudentDrawerForm
              showDrawer={isShowDrawer}
              setShowDrawer={setIsShowDrawer}
              fetchStudents={fetchStudents}
          />
      <Table
        dataSource={students}
        columns={columns(fetchStudents)}
        bordered
        title={() =>
            <>
              <Tag>Number of students</Tag>
              <Badge count={students.length} className="site-badge-count-4"/>
              <br/><br/>
              <Button onClick={()=> setIsShowDrawer(!isShowDrawer)} type="primary" shape="round" icon={<PlusOutlined />} size="small">
                Add New Student
              </Button>
            < />
              }
        pagination={{ pageSize: 50 }}
        scroll={{ y: 500 }}
        rowKey={(student) => {
          return student.id
        }}
      />
      < />
    )
  }

  return (
    <Layout style={{ minHeight: '100vh' }}>
      <Sider collapsible collapsed={isCollapsed} onCollapse={setIsCollapsed}>
        <div className='logo' />
        <Menu theme='dark' defaultSelectedKeys={['1']} mode='inline'>
          <Menu.Item key='1' icon={<PieChartOutlined />}>
            Option 1
          </Menu.Item>
          <Menu.Item key='2' icon={<DesktopOutlined />}>
            Option 2
          </Menu.Item>
          <SubMenu key='sub1' icon={<UserOutlined />} title='User'>
            <Menu.Item key='3'>Tom</Menu.Item>
            <Menu.Item key='4'>Bill</Menu.Item>
            <Menu.Item key='5'>Alex</Menu.Item>
          </SubMenu>
          <SubMenu key='sub2' icon={<TeamOutlined />} title='Team'>
            <Menu.Item key='6'>Team 1</Menu.Item>
            <Menu.Item key='8'>Team 2</Menu.Item>
          </SubMenu>
          <Menu.Item key='9' icon={<FileOutlined />}>
            Files
          </Menu.Item>
        </Menu>
      </Sider>
      <Layout className='site-layout'>
        <Header className='site-layout-background' style={{ padding: 0 }} />
        <Content style={{ margin: '0 16px' }}>
          <Breadcrumb style={{ margin: '16px 0' }}>
            <Breadcrumb.Item>User</Breadcrumb.Item>
            <Breadcrumb.Item>Bill</Breadcrumb.Item>
          </Breadcrumb>
          <div
            className='site-layout-background'
            style={{ padding: 24, minHeight: 360 }}
          >
            {renderStudents()}
          </div>
        </Content>
        <Footer style={{ textAlign: 'center' }}>BrownieBytes ©2021</Footer>
      </Layout>
    </Layout>
  )
}
export default App
