import axios from "axios";

const apiClient = axios.create({
  baseURL: "http://localhost:8080/api",
  headers: {
    "Content-Type": "application/json",
  },
});

// from https://axios-http.com/docs/interceptors
apiClient.interceptors.request.use(
  (config) => {
    const token = localStorage.getItem("auth_token");
    if (token) config.headers.Authorization = token;
    return config;
  },
  (error) => {
    return Promise.reject(error);
  }
);

apiClient.interceptors.response.use(
  (response) => response,
  (error) => {
    if (error.response?.status === 401) {
      localStorage.removeItem("auth_token");
      // TODO: Redirect to login page
    }
    return Promise.reject(error);
  }
);

export const authAPI = {
  login: async (credentials) => {
    const response = await apiClient.post("/auth/login", credentials);
    localStorage.setItem("auth_token", response.data.token);
    return response.data;
  },
  logout: async () => {
    const response = await apiClient.post("/auth/logout");
    return response.data;
  },
  getCurrentUser: async () => {
    const response = await apiClient.get("/auth/me");
    return response.data;
  },
};

export const userAPI = {
  register: async (userData) => {
    const response = await apiClient.post("/users", userData);
    return response.data;
  },

  getById: async (userId) => {
    const response = await apiClient.get(`/users/${userId}`);
    return response.data;
  },

  update: async (userId, userData) => {
    const response = await apiClient.put(`/users/${userId}`, userData);
    return response.data;
  },

  delete: async (userId) => {
    const response = await apiClient.delete(`/users/${userId}`);
    return response.data;
  },
};

export const collectionsAPI = {
  create: async (userId, collectionData) => {
    const response = await apiClient.post(`/users/${userId}/collections`, collectionData);
    return response.data;
  },
  getCollectionsByUserId: async (userId) => {
    const response = await apiClient.get(`/users/${userId}/collections`);
    return response.data;
  },
  getCollectionById: async (userId, collectionId) => {
    const response = await apiClient.get(`/users/${userId}/collections/${collectionId}`);
    return response.data;
  },
  update: async (userId, collectionId, collectionData) => {
    const response = await apiClient.put(`/users/${userId}/collections/${collectionId}`, collectionData);
    return response.data;
  },
  delete: async (userId, collectionId) => {
    const response = await apiClient.delete(`/users/${userId}/collections/${collectionId}`);
    return response.data;
  },
  addMap: async (userId, collectionId, mapData) => {
    const response = await apiClient.post(`/users/${userId}/collections/${collectionId}/maps`, mapData);
    return response.data;
  },
  getMaps: async (userId, collectionId) => {
    const response = await apiClient.get(`/users/${userId}/collections/${collectionId}/maps`);
    return response.data;
  },
  deleteMap: async (userId, collectionId, mapCode) => {
    const response = await apiClient.delete(`/users/${userId}/collections/${collectionId}/maps/${mapCode}`);
    return response.data;
  },
};

export const reviewsAPI = {
  create: async (userId, reviewData) => {
    const response = await apiClient.post(`/users/${userId}/reviews`, reviewData);
    return response.data;
  },
  getReviewsByUserId: async (userId) => {
    const response = await apiClient.get(`/users/${userId}/reviews`);
    return response.data;
  },
  getReview: async (userId, reviewId) => {
    const response = await apiClient.get(`/users/${userId}/reviews/${reviewId}`);
    return response.data;
  },
  update: async (userId, reviewId, reviewData) => {
    const response = await apiClient.put(`/users/${userId}/reviews/${reviewId}`, reviewData);
    return response.data;
  },
  delete: async (userId, reviewId) => {
    const response = await apiClient.delete(`/users/${userId}/reviews/${reviewId}`);
    return response.data;
  },
};

export const mapsAPI = {
  getMapByCode: async (mapCode) => {
    const response = await apiClient.get(`/maps/${mapCode}`);
    return response.data;
  },
  getMetrics: async (mapCode, interval, options = {}) => {
    const response = await apiClient.get(`/maps/${mapCode}/metrics/${interval}`, {
      params: options,
    });
    return response.data;
  },
  getReviews: async (mapCode) => {
    const response = await apiClient.get(`/maps/${mapCode}/reviews`);
    return response.data;
  },
};
