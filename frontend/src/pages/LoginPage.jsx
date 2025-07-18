import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { Card, Form, Input, Button, Tabs, message } from "antd";
import { UserOutlined, MailOutlined, LockOutlined } from "@ant-design/icons";
import { authAPI, userAPI } from "../services/api";

function LoginPage() {
  const [loading, setLoading] = useState(false);
  const [loginFormU] = Form.useForm();
  const [registerFormU] = Form.useForm();
  const navigate = useNavigate();

  useEffect(() => {
    checkLoggedIn();
  }, []);

  const checkLoggedIn = async () => {
    const token = localStorage.getItem("auth_token");
    if (!token) return;

    try {
      await authAPI.getCurrentUser();
      console.log("User is already logged in, redirecting to profile page.");
      navigate("/profile");
    } catch (error) {
      console.error("Error checking logged in status:", error);
      localStorage.removeItem("auth_token");
      message.error("Session expired, please log in again.");
    }
  };

  const handleLogin = async (values) => {
    setLoading(true);
    try {
      const response = await authAPI.login(values);
      message.success("Login successful!");
      console.log("Logged in:", response);
      navigate("/profile");
      loginFormU.resetFields();
    } catch (error) {
      message.error("Login failed. Please check your credentials.");
      console.error("Login error:", error);
    } finally {
      setLoading(false);
    }
  };

  const handleRegister = async (values) => {
    setLoading(true);
    try {
      const response = await userAPI.register(values);
      message.success("Registration successful!");
      console.log("Registered:", response);
      registerFormU.resetFields();
    } catch (error) {
      message.error("Registration failed. Please try again.");
      console.error("Registration error:", error);
    } finally {
      setLoading(false);
    }
  };

  const loginForm = (
    <Form form={loginFormU} name="login" onFinish={handleLogin} layout="vertical" initialValues={{ remember: true }}>
      <Form.Item name="username" rules={[{ required: true, message: "Please input your username!" }]}>
        <Input prefix={<UserOutlined />} placeholder="Username" />
      </Form.Item>
      <Form.Item name="password" rules={[{ required: true, message: "Please input your password!" }]}>
        <Input.Password prefix={<LockOutlined />} placeholder="Password" />
      </Form.Item>
      <Form.Item>
        <Button type="primary" htmlType="submit" loading={loading} block>
          Login
        </Button>
      </Form.Item>
    </Form>
  );

  const registerForm = (
    <Form form={registerFormU} name="register" onFinish={handleRegister} layout="vertical" initialValues={{ remember: true }}>
      <Form.Item name="username" rules={[{ required: true, message: "Please input your username!" }]}>
        <Input prefix={<UserOutlined />} placeholder="Username" />
      </Form.Item>
      <Form.Item name="email" rules={[{ required: true, message: "Please input your email!" }]}>
        <Input prefix={<MailOutlined />} placeholder="Email" />
      </Form.Item>
      <Form.Item name="password" rules={[{ required: true, message: "Please input your password!" }]}>
        <Input.Password prefix={<LockOutlined />} placeholder="Password" />
      </Form.Item>
      <Form.Item>
        <Button type="primary" htmlType="submit" loading={loading} block>
          Register
        </Button>
      </Form.Item>
    </Form>
  );

  const tabItems = [
    {
      key: "login",
      label: "Login",
      children: loginForm,
    },
    {
      key: "register",
      label: "Register",
      children: registerForm,
    },
  ];

  return (
    <div style={{ minHeight: "100vh", display: "flex", justifyContent: "center", alignItems: "center" }}>
      <Card style={{ width: 400 }}>
        <Tabs defaultActiveKey="login" items={tabItems} />
      </Card>
    </div>
  );
}

export default LoginPage;
