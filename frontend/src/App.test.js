import { render, screen } from '@testing-library/react';
import App from './App';

test('renders login and register headings', () => {
  render(<App />);
  expect(screen.getByText(/회원 로그인/i)).toBeInTheDocument();
  expect(screen.getByText(/회원가입/i)).toBeInTheDocument();
});
