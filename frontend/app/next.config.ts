/** @type {import('next').NextConfig} */
const nextConfig = {
  async rewrites() {
    return [
      {
        source: '/api/:path*',
        destination: 'https://writeassist.hun.it.kr/api/:path*',
      },
    ];
  },
};

export default nextConfig;
